'use strict';
import {html, useState} from 'https://unpkg.com/htm@3.1.0/preact/standalone.module.js';

import postAd from './postAd.js';


export default function CreateAd({ view, setNewAd, addMessage } ) {
    const [title, setTitle] = useState('');
    const [contact, setContact] = useState('');
    const [price, setPrice] = useState('');
    const [ad, setAd] = useState({});
    
    const Initialize = () => {
        setTitle('');
        setContact('');
        setPrice('');
        setAd({});
        if(setNewAd) {
        setNewAd(false);}
    }
    const postSingle = async (e) => {
        e.preventDefault();
        setAd(ad => {
            ad.title = title;
            ad.contact = contact;
            ad.price = price;
        });
        await postAd(ad, setAd, addMessage);
        Initialize();
    };

    return html`
        <div class=${view === 'api' ? 'card card-api-fiori shadow-lg' : 'card card-fiori shadow-lg'}  >
            <form onSubmit=${postSingle} >
                <div class="card-body">
                    ${view === 'api' ?
                        html`<div class="card-title" >Create ad</div>` 
                        : html`<input class="card-title" style="width: 8rem" type='text' value=${title} placeholder="add title!" onChange=${(e) => { setTitle(e.target.value) }} ></input>`
                    }
                    <input class="ad-input" type='text' value=${contact} placeholder="add contact" onChange=${(e) => { setContact(e.target.value) }} ></input>
                    <span class=${view === 'api' ? '' : 'badge bg-danger rounded-pill'} >
                        <input class="ad-input" type='text' value=${price} placeholder="add price" onChange=${(e) => { setPrice(e.target.value) }} ></input>
                    </span>
                    <div>
                        <button class="btn btn-primary api-button" >${view === 'api' ? 'POST' : 'Subimt'}</button>
                    </div>
                </div>
            </form>
        </div>
        `;
};