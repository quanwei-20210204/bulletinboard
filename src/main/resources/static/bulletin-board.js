'use strict';
import {html, useEffect, useState} from 'https://unpkg.com/htm@3.1.0/preact/standalone.module.js';

import DisplayAdOnBoard from './components/display/displayAdOnBoard.js';
import dataLoad from './utils/dataLoad.js';
import getAll from './components/display/getAll.js';
import AddButton from './components/create/addButton.js';
import DeleteButton from './components/delete/deleteButton.js';
import UpdateButton from './components/update/updateButton.js';
import CreateAd from './components/create/createAd.js';
import UpdateAd from './components/update/updateAd.js';

// Bulletin Board
export default function BulletinBoard( {view} ) {
    const [ads, setAds] = useState([]);
    const [newAd, setNewAd] = useState(false);
    
    const [updateAdId, setUpdateAdId] = useState('');
    const [adChanged, setAdChanged] = useState(false);

    useEffect( () => {
        getAll(ads, setAds);
        setAdChanged(false);
    }, [newAd, adChanged]);
    useEffect( () => {
        
        dataLoad();
    }, [])
    return html`
        <div class="container">
            <div class='row row-col-auto' >
                ${ads.length > 0 ? ads.map(el => html`
                    ${el ? html`
                    ${updateAdId === el.id ? html`<${UpdateAd} view=${view} ad=${el} setAdChanged=${setAdChanged} setUpdateAdId=${setUpdateAdId} />` :
                            html`
                            <div class="card card-fiori shadow-lg" >
                                <${DisplayAdOnBoard} ad=${el} />
                                <${UpdateButton} ad=${el} setUpdateAdId=${setUpdateAdId} />
                                <${DeleteButton} ad=${el} setAdChanged=${setAdChanged} />
                            </div>
                            `}
                        `
                    : ''}`)
                : ''}
                ${  !newAd ? html`<${AddButton} setNewAd=${setNewAd} /> ` : html`<${CreateAd} view=${view} setNewAd=${setNewAd} />`}
            </div>
        </div>
        `;

};