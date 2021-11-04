'use strict';
import {html, useState} from 'https://unpkg.com/htm@3.1.0/preact/standalone.module.js';

import DisplayAd from './displayAd.js';
import getAll from './getAll.js';

export default function DisplayAllAdsAPI( { addMessage }) {
    const [ads, setAds] = useState([]);
    const handleClick = () => {
        getAll(ads, setAds, addMessage);
    }

    return html`
        <div class="card card-api-fiori shadow-lg" >
            <div class="card-body" >
                <div class="card-title" >Display all ads
                    <div class="m-2">
                        <button class="btn btn-primary api-button" onClick=${handleClick} >GET</button>
                    </div>
                        ${ads && html`
                            <ol class="list-group list-group-numbered">
                                ${ads ? ads.map(el => html`<${DisplayAd} ad=${el} />`) : ''}
                            </ol>
                        `}
                </div>
            </div>  
        </div>
        `;
};