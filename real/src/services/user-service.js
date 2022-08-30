import axios from 'axios';
export const BASE_URL = 'http://localhost:9292/';

export const loginUser = (loginDetails) => {
    return axios
        .post(BASE_URL + "api/v1/auth/login", loginDetails, {
            headers: {
                "Content-Type": "application/json",
            },
        }).then((response) => response.data.token)
}