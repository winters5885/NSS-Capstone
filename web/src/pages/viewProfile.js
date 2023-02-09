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
           // this.displayMemberProfile();
        }

    /**
     * Once the client is loaded, get the member metadata and member information.
     */

    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const memberId = urlParams.get('id');
        console.log("Inside clientLoaded viewProfile.js memberId: ", memberId);
        const member = await this.client.getMember(memberId);
        console.log("Inside clientLoaded viewProfile.js member: ", member);
        this.displayMemberProfile(member);
    }

     displayMemberProfile(member) {
        if (member == null) {
            return;
        }

        let memberHtml = '';
            memberHtml += `
                <li class="song">
                    <span class="attribute">${"Name: " + member.name }<br>
                    <span class="attribute"></br>${"Age: " + member.age} <br></span>
                    <span class="attribute"></br>${"Gender: " + member.gender}<br></span>
                    <span class="attribute"></br>${"Address: " + member.address}<br></span>
                    <span class="attribute"></br>${"Email Address: " + member.emailAddress}<br></span>
                    <span class="attribute"></br>${"Phone Number: " + member.phoneNumber}</span>
                </li>
            `;

        document.getElementById('member').innerHTML = memberHtml;
     
        // document.getElementById('member').innerText = member.name;
        // document.getElementById('member').innerText = member.age;
         console.log("Inside displayMemberProfile method member: ", member);
        
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