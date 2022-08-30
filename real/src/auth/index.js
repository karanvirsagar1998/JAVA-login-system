export const isLoggedIn = () => {
    return localStorage.getItem("data") ? true : false;
}

export const doLogin = (data) => {
    return localStorage.setItem("data", JSON.stringify(data))
}

export const doLogout = () => {
    let res = localStorage.removeItem("data")
    return res == null || undefined ? true : false;
}

export const getCurrentUserDetail = () => {
    return isLoggedIn() ? JSON.parse(localStorage.getItem("data")) : false;
}