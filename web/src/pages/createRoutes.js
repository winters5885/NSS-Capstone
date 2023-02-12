import AscendNashvilleClient from '../api/ascendNashvilleClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create routes page of the website.
 */
class CreateRoutes extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToViewRoutes'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewRoutes);
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
     * Method to run when the create route submit button is pressed. Call the AscendNashville service to create the
     * route.
     */
    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const createButton = document.getElementById('create');
        const origButtonText = createButton.innerText;
        createButton.innerText = 'Loading...';

        const routeId = document.getElementById('routeId').value;
        const difficultyRating = document.getElementById('difficultyRating').value;
        const routeType = document.getElementById('routeType').value;
        const memberRating = 1;

        const route = await this.client.createRoutes(routeId, difficultyRating, routeType, memberRating, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('route', route);
    }

    /**
     * When the route is updated in the datastore, redirect to the view view routes page.
     */
    redirectToViewRoutes() {
        console.log("Inside redirectToViewRoutes method.")
        const route = this.dataStore.get('route');
        if (route != null) {
            window.location.href = `/viewRoutes.html?id=${route.routeId}`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createRoute = new CreateRoutes();
    createRoute.mount();
};

window.addEventListener('DOMContentLoaded', main);