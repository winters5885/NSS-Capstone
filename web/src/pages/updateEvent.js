import AscendNashvilleClient from '../api/ascendNashvilleClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the update event page of the website.
 */
class UpdateEvent extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToHomePage'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToHomePage);
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the AscendNashvilleClient.
     */
    mount() {
        document.getElementById('create').addEventListener('click', this.submit);
        this.header.addHeaderToPage();
        this.client = new AscendNashvilleClient();
    }

    /**
     * Method to run when the Update Event submit button is pressed. Call the AscendNashville service to update the
     * event.
     */
    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const createButton = document.getElementById('create');
        const origButtonText = createButton.innerText;
        createButton.innerText = 'Loading...';

        const urlParams = new URLSearchParams(window.location.search);
        const eventIdFromURL = urlParams.get('id'); 

        const date = document.getElementById('date').value;
        const eventDetails = document.getElementById('eventDetails').value;

        const event = await this.client.updateEvent(eventIdFromURL, date, eventDetails,(error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        console.log("Inside submit, event:", event);
        this.dataStore.set('event', event);
    }

    /**
     * When the event is updated in the datastore, redirect to the home page.
     */
    redirectToHomePage() {
        console.log("Inside redirectToHomePage method in updateEvents.js.")
        const event = this.dataStore.get('event');
        if (event != null) {
            window.location.href = `/index.html`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const updateEvent = new UpdateEvent();
    updateEvent.mount();
};

window.addEventListener('DOMContentLoaded', main);