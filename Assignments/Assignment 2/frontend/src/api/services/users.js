import { BASE_URL, HTTP } from "../http";
import getToken from "@/api/utils";

export default {
    getAll() {
        return HTTP.get(BASE_URL + "/users/find-all", { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    deleteById(Id) {
        return HTTP.post(BASE_URL + "/users/delete", Id, { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    create(UserData) {
        return HTTP.post(BASE_URL + "/users/save", UserData, { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    update(UserData) {
        return HTTP.post(BASE_URL + "/users/update", UserData, { headers: getToken() }).then((response) => {
            return response.data;
        });
    }
};