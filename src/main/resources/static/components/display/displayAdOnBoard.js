'use strict';
import {html} from 'https://unpkg.com/htm@3.1.0/preact/standalone.module.js';

export default function DisplayAdOnBoard( {ad} ) {
    return html`
        <div class="card-body d-flex justify-content-between align-items-start">
            <div class="card-title" >${ad.title}</div>
            <span class="badge bg-danger rounded-pill position-absolute top-0 end-0 card-badge" >${ad.price ? html` ${ad.price}€` : '-'}</span>
        </div>
        <p class="card-text" style="margin: 1rem "> ${ad.contact ? html`by ${ad.contact}` : ''}</p>
    `
};