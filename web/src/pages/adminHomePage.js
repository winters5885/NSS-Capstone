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
        this.bindClassMethods(['mount', 'submit', 'displayEvents', 'displayRoutes'], this);
        this.dataStore = new DataStore();
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

        let eventHtml = '';
        let event;
        for (event of eventsList) {
            var specificEventId = event.eventId;
            console.log("specificEventId: " + specificEventId);

            eventHtml += `
                <li class="route">
                        <span class="attribute"></br>${"Date: " + event.date} <br>
                        <span class="attribute"></br>${"Event Details: " + event.eventDetails}<br><span>
                        <button class="button" onclick="location.href = 'updateEvent.html?eventId=' + ${specificEventId}"></br>Update this Event<br><button>
                        <button class="button" onclick="location.href = 'deleteEvent.html?eventId=' + ${specificEventId}"></br>Delete This Event</br><button>  
                </li>
            `;
        }
        
         document.getElementById('eventsList').innerHTML = eventHtml;
         console.log("Inside displayRoutes AdminHomePage method route: ", event);
     }

     async displayRoutes() {
        var routesList = await this.client.getRoutes();
  
        let routeHtml = '';
        let route;
        for (route of routesList) {
            var specificRouteId = route.routeId;
            console.log("specificRouteId: " + specificRouteId);

            routeHtml += `
                <li class="route">
                        <span class="attribute">${"Route Number: " + route.routeId }<br>
                        <span class="attribute"></br>${"Difficulty Rating: " + route.difficultyRating} <br></span>
                        <span class="attribute"></br>${"Route Type: " + route.routeType}<br></span>
                        <button class="button" onclick="location.href = 'deleteRoute.html?routeId=' + ${specificRouteId}"></br>Delete This Route</br><button>  
                </li>
            `;
        }
         document.getElementById('route').innerHTML = routeHtml;
         console.log("Inside displayRoutes method route: ", route);
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