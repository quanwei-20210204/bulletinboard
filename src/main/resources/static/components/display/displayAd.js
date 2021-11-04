'use strict';
import {html} from 'https://unpkg.com/htm@3.1.0/preact/standalone.module.js';

export default function displayAd({ ad }) {
    if (ad && ad.title) {
        return (html`
            <li key=${ad.id} class="list-group-item d-flex justify-content-between align-items-start"  >
                <div class="ms-2 me-auto">
                    <div class="fw-bold">${ad.title}</div>
                     ${ad.contact ? html`by ${ad.contact}` : ''}  
                </div>
                <span class="badge bg-primary rounded-pill">${ad.price ? html` ${ad.price}€` : '-'}</span>
            </li>`);
    }
};