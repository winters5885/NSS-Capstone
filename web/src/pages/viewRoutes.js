import AscendNashvilleClient from '../api/ascendNashvilleClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view routes page of the website.
 */
class ViewRoutes extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayRoutes'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        console.log("viewRoutes constructor");
    }

    /**
     * Add the header to the page and load the AscendNashvilleClient.
     */
        mount() {
            this.header.addHeaderToPage();
            this.client = new AscendNashvilleClient();
            this.clientLoaded();
            this.displayRoutes();
        }

    /**
     * Once the client is loaded, get the route metadata and route information.
     */

    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const routeId = urlParams.get('id');
        console.log("Inside clientLoaded viewRoutes.js routeId: ", routeId);
    }

    async displayRoutes() {
        var routesList = await this.client.getRoutes();
  
        let routeHtml = '';
        let route;
        for (route of routesList) {
            routeHtml += `
                <li class="route">
                        <span class="attribute">${"RouteId: " + route.routeId }<br>
                        <span class="attribute"></br>${"Difficulty Rating: " + route.difficultyRating} <br></span>
                        <span class="attribute"></br>${"Route Type: " + route.routeType}<br></span>  
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
    const viewRoutes = new ViewRoutes();
    viewRoutes.mount();
};

window.addEventListener('DOMContentLoaded', main);