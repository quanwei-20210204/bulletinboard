'use strict';

import formatedDate from "../../utils/formatedDate.js";

export default async function getAll(ads, setAds, addMessage) {
    try {
        const response = await fetch('/api/v1/ads', {
            headers: { 'content-type': 'application/json' },
            method: 'GET'
        })
        const jsonResponse = await response.json();
        setAds(jsonResponse);
        if (addMessage) {
            addMessage('GET-ALL', response.status, response.statusText, response.url, formatedDate());
        }
    } catch (err) {
        if (addMessage) {
            addMessage('GET-ALL', response.status, response.statusText, response.url);
        }
    }
};