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
        const memberId = urlParams.get('id');
        console.log("Inside clientLoaded viewProfile.js memberId: " + memberId);
        document.getElementById('loading').innerText = "Loading Member ...";
        const member = await this.client.getMember(memberId);
        console.log("Inside clientLoaded viewProfile.js member: " + member);
        this.dataStore.set('member', member);
    }

    async displayMemberProfile() {
        
        const urlParams = new URLSearchParams(window.location.search);
        const memberIdFromURL = urlParams.get('id');
       
        console.log("Inside displayMemberProfile method in viewProfile.js, memberIdFromURL: " + memberIdFromURL);

        document.getElementById("member").innerHTML += "<br>" + "http://localhost:8000/memberProfile.html?id=" +
                        memberIdFromURL + "</br>";
        console.log("viewProfile.js line 49.")
        const jsonList = await this.client.getMember(memberIdFromURL);
        console.log("jsonList: " + jsonList);
        //var memberProfile = urlParams.get('memberId');


        document.getElementById("member").innerHTML += "<br>"+ member + ", " + 
                member +"</br>";
    }
    }



/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewProfile = new ViewProfile();
    viewProfile.mount();
};

window.addEventListener('DOMContentLoaded', main);