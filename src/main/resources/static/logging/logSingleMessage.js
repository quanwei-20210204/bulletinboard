'use strict';
import {html} from 'https://unpkg.com/htm@3.1.0/preact/standalone.module.js';

export default function Log({ message }) {

    if (message && message.method) {
        return (html`
            <li class="list-group-item bg-light d-flex justify-content-between align-items-start"  >
                <div class="ms-3 me-auto">
                    <div class="fw-bold">${message.method}</div>
                     ${message.text ? html`Status: ${message.text}` : ''} <span style="font-size: 0.8rem" >${message.date}</span>
                </div>                
                ${message.status > 400 ? 
                    html`<span class="badge bg-danger rounded-pill">${message.status ? html` ${message.status}` : '-'}</span>`
                    :
                    html`<span class="badge bg-success rounded-pill">${message.status ? html` ${message.status}` : '-'}</span>`
                 }

            </li>`);
    }
};
