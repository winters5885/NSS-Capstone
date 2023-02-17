import AscendNashvilleClient from '../api/ascendNashvilleClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the delete route page of the website.
 */
class DeleteRoute extends BindingClass {
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
     * Method to run when the Delete button is pressed. Call the AscendNashville service to delete the
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
        const routeIdFromURL = urlParams.get('routeId'); 
        //const eventId = document.getElementById('eventId').value;

        const route = await this.client.deleteRoute(routeIdFromURL, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        console.log("Inside submit for DeleteEvent, route:", route);
        this.dataStore.set('route', route);
    }

    /**
     * When the event is deleted in the datastore, redirect to the home page.
     */
    redirectToHomePage() {
        console.log("Inside redirectToHomePage method in deleteRoute.js.")
        const route = this.dataStore.get('route');
        if (route != null) {
            window.location.href = `/adminIndex.html`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const deleteRoute = new DeleteRoute();
    deleteRoute.mount();
};

window.addEventListener('DOMContentLoaded', main);