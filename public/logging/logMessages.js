'use strict';
import {html} from 'https://unpkg.com/htm@3.1.0/preact/standalone.module.js';

import Log from './logSingleMessage.js'

export default function LogMessages({ messages }) {
    if (messages) {
        return html`
        <div class="card card-api-fiori shadow-lg" style="width: 36rem">
            <div class="card-body">
                <div class="card-title" >Log Messages</div>
                    ${messages && html`
                        <ul class="list-group list-group-flush">
                            ${messages ? messages.map(el => html`<${Log} message=${el} />`) : ''}
                        </ul>
                    `}
            </div>  
        </div>
    `
    }
}