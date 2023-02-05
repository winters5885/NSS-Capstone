import AscendNashvilleClient from '../api/ascendNashvilleClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create member profile page of the website.
 */
class CreateMemberProfile extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToViewMemberProfile'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewMemberProfile);
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
     * Method to run when the create profile submit button is pressed. Call the AscendNashville service to create the
     * member profile.
     */
    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const createButton = document.getElementById('create');
        const origButtonText = createButton.innerText;
        createButton.innerText = 'Loading...';

        const name = document.getElementById('name').value;
        const age = document.getElementById('age').value;
        const gender = document.getElementById('gender').value;
        const phoneNumber = document.getElementById('phoneNumber').value;
        const address = document.getElementById('address').value;
        const emailAddress = document.getElementById('emailAddress').value;

        const member = await this.client.createMember(name, age, gender, phoneNumber, address, emailAddress, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('member', member);
    }

    /**
     * When the member is updated in the datastore, redirect to the view member profile page.
     */
    redirectToViewMemberProfile() {
        const member = this.dataStore.get('member');
        if (member != null) {
            window.location.href = `/memberProfile.html?id=${member.id}`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createMember = new CreateMemberProfile();
    createMember.mount();
};

window.addEventListener('DOMContentLoaded', main);