import AscendNashvilleClient from '../api/ascendNashvilleClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create home page of the website.
 */
class AdminHomePage extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToUpdateEventPage);
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the AscendNashvilleClient.
     */
    mount() {
        //document.getElementById('create').addEventListener('click', this.submit);
        this.header.addHeaderToPage();
        this.client = new AscendNashvilleClient();
        this.displayEvents();
    }

    /**
     * Method to run when the create member submit button is pressed. Call AscendNashville to create the
     * member.
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
        var eventIdtoUpdate = urlParams.get('eventId');

        const event = await this.client.getEvent(eventIdtoUpdate, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('event', event);
        // evt.preventDefault();

        // const errorMessageDisplay = document.getElementById('error-message');
        // errorMessageDisplay.innerText = ``;
        // errorMessageDisplay.classList.add('hidden');

        // const createButton = document.getElementById('create');
        // const origButtonText = createButton.innerText;
        // createButton.innerText = 'Loading...';
    }

     async displayEvents() {
        var eventsList = await this.client.getEvents();
        console.log("Inside displayEvents AdminHomePage method eventsList:" + eventsList);

        const createButton = document.getElementById('create');
        //const origButtonText = createButton.innerText;
        //createButton.innerText = 'Loading...';

        let eventHtml = '';
        let event;
        for (event of eventsList) {
            eventHtml += `
                <li class="route">
                        <span class="attribute">${"Date: " + event.date} <br>
                        <span class="attribute"></br>${"Event Details: " + event.eventDetails}<br><span>
                        <span class="button" onclick="location.href = 'updateEvent.html'"></br>${"Update this Event"}<br><span>
                        <span class="button" onclick="location.href = 'deleteEvent.html'"></br>${"Delete this Event"}<br></span>  
                </li>
            `;
        }
        
         //this.dataStore.set('event', event);
         document.getElementById('eventsList').innerHTML = eventHtml;
         console.log("Inside displayRoutes AdminHomePage method route: ", event);
     }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const adminHomePage = new AdminHomePage();
    adminHomePage.mount();
};

window.addEventListener('DOMContentLoaded', main);