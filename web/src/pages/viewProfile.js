import AscendNashvilleClient from '../api/ascendNashvilleClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view member page of the website.
 */
class ViewProfile extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayMemberProfile'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        console.log("viewmember constructor");
    }

        /**
     * Add the header to the page and load the AscendNashvilleClient.
     */
        mount() {
            this.header.addHeaderToPage();
            this.client = new AscendNashvilleClient();
            this.clientLoaded();
            this.displayMemberProfile();
        }

    /**
     * Once the client is loaded, get the member metadata and member information.
     */

    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const memberId = urlParams.get('memberId');
        document.getElementById('memberId').innerText = "Loading Member ...";
        const member = await this.client.getMember(memberId);
        this.dataStore.set('member', member);
    }

    async displayMemberProfile() {
        const urlParams = new URLSearchParams(window.location.search);
        var member = await this.client.getMember();

        var memberProfile = urlParams.get('memberId');

        if (!member.includes(memberProfile)) {
            document.getElementById("member").innerHTML = "Not a valid member.";
        } else {
            document.getElementById("member").innerHTML = categoryChosen;
        }
    }
    // addMemberProfileDetailsToPage() {
    //     const member = this.dataStore.get('member');

    //     document.getElementById(
        
    //     )
    }



/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewProfile = new ViewProfile();
    viewProfile.mount();
};

window.addEventListener('DOMContentLoaded', main);