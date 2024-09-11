export const login = (credentials) => {
  const loginUrl = `/login?username=${credentials.username}&password=${credentials.password}`;
  // all requests go to proxy + url

  // fetch will return something called a Promise
  // frontend call backend - send request, included function
  // fetch returns a Promise, which is an object
  return (
    // fetch is used to call backend api
    // fetch returns a promise
    // we do't konw how long backend can return the results
    // 
    fetch(loginUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
    })
      // if fetch runs normally, run .then(), if something abnormal happens, run .catch()
      .then((response) => {
        // Promise.then() returns Promise - response is wrapped in Promise
        if (response.status < 200 || response.status >= 300) {
          throw Error("Fail to log in");
        }
      })
  ); // returns a promise
  // .then(() => {})
  // .catch(() => {})
  // .then(() => {})
  // .finally
};

export const signup = (data) => {
  const signupUrl = "/signup"; 
  // path defined in backend

  return fetch(signupUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data), // convert a JavaScript object (data) into a JSON string representation.
  }).then((response) => {
    if (response.status < 200 || response.status >= 300) {
      throw Error("Fail to sign up");
    }
  });
};

export const getRestaurants = () => {
  return fetch("/restaurants/menu").then((response) => {
    if (response.status < 200 || response.status >= 300) {
      throw Error("Fail to get restaurants");
    }

    return response.json();
  });
}; 

export const getMenus = (restId) => {
  return fetch(`/restaurant/${restId}/menu`).then((response) => {
    if (response.status < 200 || response.status >= 300) {
      throw Error("Fail to get menus");
    }

    return response.json(); // reads the response body and parses it as JSON, returns a promise that resolves with the result of parsing the body text as JSON
    // only fetch funtion can return response.json()
  });
};

/*
        
        What's the difference between JSON and JS Object?
        JSON - string, but it has the format of a obj i.e. {key: value}
        - key: must be double quoted
        
        JS Object - Object {key: value}
        - key: double quoted?
        
*/

export const addItemToCart = (itemId) => {
  const payload = {
    menu_id: itemId,
  };

  return fetch(`/cart`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(payload),
  }).then((response) => {
    if (response.status < 200 || response.status >= 300) {
      throw Error("Fail to add menu item to shopping cart");
    }
  });
};

        
export const getCart = () => {
  return fetch("/cart").then((response) => {
    if (response.status < 200 || response.status >= 300) {
      throw Error("Fail to get shopping cart data");
    }

    return response.json();
  });
};

export const checkout = () => {
  return fetch("/cart/checkout", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
  }).then((response) => {
    if (response.status < 200 || response.status >= 300) {
      throw Error("Fail to checkout");
    }
  });
};



// util.js is the file connects backend and frontend
