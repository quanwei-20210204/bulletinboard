'use strict';

import formatedDate from '../../utils/formatedDate.js';

export default async function updateSingle(id, ad, addMessage) {
    try {
        const response = await fetch(`/api/v1/ads/${id}`, {
            headers: { 'content-type': 'application/json' },
            method: 'PUT',
            body: JSON.stringify(ad)
        });
        if(addMessage){
            addMessage('PUT', response.status, response.statusText, response.url, formatedDate());
        }
    } catch (err) {
        if (addMessage) {
            addMessage('PUT', response.status, response.statusText, response.url);
        }   
        return
    }
};