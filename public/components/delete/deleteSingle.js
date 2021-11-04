'use strict';

import formatedDate from '../../utils/formatedDate.js';

export default async function deleteSingle( id, setId, addMessage ) {
    try {
        const response = await fetch(`/api/v1/ads/${id}`, {
            headers: { 'content-type': 'application/json' },
            method: 'DELETE'
        });
        setId('');
        if (addMessage) {
            addMessage('DELETE', response.status, response.statusText, response.url, formatedDate());
        }
    } catch (err) {
        if (addMessage) {
            addMessage('DELETE', response.status, response.statusText, response.url);
        }
    }
};