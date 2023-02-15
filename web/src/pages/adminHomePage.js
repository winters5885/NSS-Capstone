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
        this.bindClassMethods(['mount', 'submit', 'displayEvents', 
                'redirectToCreateProfile', 'redirectToCreateRoutesPage'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToCreateProfile);
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
                        <span class="attribute">${"EventId: " + event.eventId }<br>
                        <span class="attribute"></br>${"Date: " + event.date} <br></span>
                        <span class="attribute"></br>${"Event Details: " + event.eventDetails}<br><span>
                        <span class="button"></br>${createButton}<br></span>  
                </li>
            `;
        }
         document.getElementById('eventsList').innerHTML = eventHtml;
         console.log("Inside displayRoutes AdminHomePage method route: ", event);
     }
    /**
     * When the member is updated in the datastore, redirect to the view profile page.
     */
    redirectToCreateProfile() {
        const member = this.dataStore.get('member');
        if (member != null) {
            window.location.href = `/createMemberProfile.html?id=${member.id}`;
        }
    }

    /**
     * When the route is updated in the datastore, redirect to the view routes page.
     */
    redirectToCreateRoutesPage() {
        const route = this.dataStore.get('route');
        if (route != null) {
            window.location.href = `/createRoutes.html?id=${route.id}`;
        }
    }

    /**
     * When the route is updated in the datastore, redirect to the view routes page.
     */
    redirectToUpdateEventPage() {
        const event = this.dataStore.get('event');
        if (event != null) {
            window.location.href = `/updateEvent.html?id=${event.id}`;
        }
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