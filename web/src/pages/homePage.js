import AscendNashvilleClient from '../api/ascendNashvilleClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create home page of the website.
 */
class HomePage extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'displayEvents', 'displayRoutes', 'hideAdminButton'], this);
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
        this.displayRoutes();
        this.hideAdminButton();
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
        console.log("Inside displayEvents method eventsList:" + eventsList);
        let eventHtml = '';
        let event;
        for (event of eventsList) {
            eventHtml += `
                <li class="route">
                        <span></br>${"Event Date: " + event.date} <br>
                        <span></br>${"Event Details: " + event.eventDetails}<br></span>  
                </li>
            `;
        }
         document.getElementById('eventsList').innerHTML = eventHtml;
         console.log("Inside displayRoutes method event: ", event);
     }

     async displayRoutes() {
        var routesList = await this.client.getRoutes();
  
        let routeHtml = '';
        let route;
        for (route of routesList) {
            routeHtml += `
                <li class="route">
                        <span class="attribute">${"Route Number: " + route.routeId }<br>
                        <span class="attribute"></br>${"Difficulty Rating: " + route.difficultyRating} <br></span>
                        <span class="attribute"></br>${"Route Type: " + route.routeType}<br></span>  
                </li>
            `;
        }
         document.getElementById('route').innerHTML = routeHtml;
         console.log("Inside displayRoutes method route: ", route);
     }

     async hideAdminButton() {
        var isLoggedIn = await this.client.verifyLogin();
        console.log("Inside hideAdminButton, isLoggedIn: " + isLoggedIn);
        if(isLoggedIn == false) {
            document.getElementById('admin-button').style.visibility = 'hidden';
        }
     }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const homePage = new HomePage();
    homePage.mount();
};

window.addEventListener('DOMContentLoaded', main);