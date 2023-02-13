import AscendNashvilleClient from '../api/ascendNashvilleClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view routes page of the website.
 */
class ViewFilteredRoutes extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayFilteredRoutes'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        console.log("viewFilteredRoutes constructor");
    }

    /**
     * Add the header to the page and load the AscendNashvilleClient.
     */
        mount() {
            this.header.addHeaderToPage();
            this.client = new AscendNashvilleClient();
            this.clientLoaded();
            this.displayFilteredRoutes();
        }

    /**
     * Once the client is loaded, get the route metadata and route information.
     */

    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const routeId = urlParams.get('id');
        console.log("Inside clientLoaded viewRoutes.js routeId: ", routeId);
    }

    async displayFilteredRoutes() {

        const urlParams = new URLSearchParams(window.location.search);
        const displayDifficultyFromURL = urlParams.get('displayDifficulty');
        
        const jsonList = await this.client.getFilteredRoutesList(displayDifficultyFromURL);
        
        if (jsonList.length == 0) {
            document.getElementById("filteredRoutesList").innerHTML = "Return list is empty."
        }

        for (var i = 0; i < jsonList.length; i++) {
        
            var route = jsonList[i];

                document.getElementById("filteredRoutesList").innerHTML += "<br>"+ route.routId + ", " + 
                route.difficultyRating + " , " + route.routeType + "</br>";
        }
    }
        // var routesList = await this.client.getRoutes();
  
        // let routeHtml = '';
        // let route;
        // for (route of routesList) {
        //     routeHtml += `
        //         <li class="route">
        //                 <span class="attribute">${"RouteId: " + route.routeId }<br>
        //                 <span class="attribute"></br>${"Difficulty Rating: " + route.difficultyRating} <br></span>
        //                 <span class="attribute"></br>${"Route Type: " + route.routeType}<br></span>  
        //         </li>
        //     `;
        // }
        //  document.getElementById('route').innerHTML = routeHtml;
        //  console.log("Inside displayRoutes method route: ", route);
     //}
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewFilteredRoutes = new ViewFilteredRoutes();
    viewFilteredRoutes.mount();
};

window.addEventListener('DOMContentLoaded', main);