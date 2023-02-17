import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call AscendNashville.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
  */
export default class AscendNashvilleClient extends BindingClass {

    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 
                                'logout', 'getMember', 'createMember', 
                                'getRoutes', 'createRoutes', 'getEvents',
                                'createEvent', 'deleteRoute', 'getEvent', 'verifyLogin'];
        this.bindClassMethods(methodsToBind, this);

        this.authenticator = new Authenticator();;
        this.props = props;
        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }

            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async verifyLogin() {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();

        if (!isLoggedIn) {
            return false;
        }
            return true;
    }
    async login() {
        await this.authenticator.login();
    }

    async logout() {
        await this.authenticator.logout();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }

    /**
     * Gets the member for the given ID.
     * @param memberId Unique identifier for a member
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The member's metadata.
     */
    async getMember(memberId, errorCallback) {
        try {
            const response = await this.axiosClient.get(`member/${memberId}`);
            console.log("Inside the client getMember method, response: ",response);
            return response.data.member;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Create a new member.
     * @param name The name of the member to create.
     * @param age
     * @param gender
     * @param phoneNumber
     * @param address
     * @param emailAddress
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The member that has been created.
     */
    async createMember(name, age, gender, phoneNumber, address, emailAddress, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create a profile.");
            const response = await this.axiosClient.post(`member`, {
                name: name,
                age: age,
                gender: gender,
                phoneNumber: phoneNumber,
                address: address,
                emailAddress: emailAddress
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.member;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

        /**
     * Gets the routes from the database.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The routes metadata.
     */
    async getRoutes(errorCallback) {
        try {
            const response = await this.axiosClient.get(`route`);
            console.log("Inside the client getRoutes method, response: ",response);
            return response.data.routes;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Delete an existing route.
     * @param routeId The routeId of the route to delete.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The route that has been deleted.
     */
    async deleteRoute(routeId,  errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can delete a route.");
            const response = await this.axiosClient.delete(`route/${routeId}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            console.log("Inside deleteEvent in the client, response", response);
            return response.data.routeModel;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }
    /**
     * Create a new route.
     * @param routeId The routeId of the route to create.
     * @param difficultyRating
     * @param routeType
     * @param memberRating
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The route that has been created.
     */
    async createRoutes(routeId, difficultyRating, routeType, memberRating, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create a route.");
            const response = await this.axiosClient.post(`route`, {
                routeId: routeId,
                difficultyRating: difficultyRating,
                routeType: routeType,
                memberRating: memberRating,
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.route;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Gets the events from the database.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The events metadata.
     */
    async getEvents(errorCallback) {
        try {
            const response = await this.axiosClient.get(`event`);
            console.log("Inside the client getEvents method, response: ",response);
            return response.data.eventsList;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Gets the event for the given ID.
     * @param eventId Unique identifier for a member
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The event's metadata.
     */
        async getEvent(eventId, errorCallback) {
            try {
                const response = await this.axiosClient.get(`event/${eventId}`);
                console.log("Inside the client getEvent method, response: ",response);
                return response.data.eventModel;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }

    /**
     * Create a new event.
     * @param eventId The eventId of the event to create.
     * @param date
     * @param eventDetails
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The event that has been created.
     */
    async createEvent(date, eventDetails, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create an event.");
            const response = await this.axiosClient.post(`event`, {
                date: date,
                eventDetails: eventDetails,
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.event;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Update an existing event.
     * @param eventId The eventId of the event to update.
     * @param date
     * @param eventDetails
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The event that has been updated.
     */
    async updateEvent(eventId, date, eventDetails, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create an event.");
            const response = await this.axiosClient.put(`event/${eventId}`, {
                eventId: eventId,
                date: date,
                eventDetails: eventDetails,
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            console.log("Inside updateEvent in the client, response", response);
            return response.data.eventModel;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Delete an existing event.
     * @param eventId The eventId of the event to update.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The event that has been deleted.
     */
        async deleteEvent(eventId,  errorCallback) {
            try {
                const token = await this.getTokenOrThrow("Only authenticated users can create an event.");
                const response = await this.axiosClient.delete(`event/${eventId}`, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                console.log("Inside deleteEvent in the client, response", response);
                return response.data.eventModel;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }
    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(error, errorCallback) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }

        if (errorCallback) {
            errorCallback(error);
        }
    }
}