'use strict';

import formatedDate from '../../utils/formatedDate.js';

export default async function postAd (ad, setAd, addMessage) {
    try {
        const response = await fetch(`/api/v1/ads`, {
            headers: { 'content-type': 'application/json' },
            method: 'POST',
            body: JSON.stringify(ad)
        });
        const jsonResponse = await response.json();
        setAd(jsonResponse);
        if (addMessage) {
            addMessage('POST', response.status, response.statusText, response.url, formatedDate());
        }
    } catch (err) {
        if (addMessage) {
            addMessage('POST', response.status, response.statusText, response.url);
        }
    }; 


};