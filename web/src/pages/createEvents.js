import AscendNashvilleClient from '../api/ascendNashvilleClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create routes page of the website.
 */
class CreateEvents extends BindingClass {
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
        document.getElementById('create-event-form').addEventListener('submit', this.submit);
        this.header.addHeaderToPage();
        this.client = new AscendNashvilleClient();
    }

    /**
     * Method to run when the Add Event submit button is pressed. Call the AscendNashville service to create the
     * event.
     */
    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const date = document.getElementById('date').value;
        const eventDetails = document.getElementById('eventDetails').value;

        const event = await this.client.createEvent(date, eventDetails,(error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('event', event);
    }

    /**
     * When the event is updated in the datastore, redirect to the home page.
     */
    redirectToHomePage() {
        console.log("Inside redirectToHomePage method.")
        const event = this.dataStore.get('event');
        if (event != null) {
            window.location.href = `/adminIndex.html`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createEvent = new CreateEvents();
    createEvent.mount();
};

window.addEventListener('DOMContentLoaded', main);