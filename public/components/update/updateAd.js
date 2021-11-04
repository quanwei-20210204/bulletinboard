'use strict';
import {html, useEffect, useState} from 'https://unpkg.com/htm@3.1.0/preact/standalone.module.js';

import updateSingle from './updateSingle.js';

export default function UpdateAd({ ad, view, setAdChanged, setUpdateAdId, addMessage } ) {
    const [id, setId] = useState('');
    const [title, setTitle] = useState('');
    const [contact, setContact] = useState('');
    const [price, setPrice] = useState('');
    const Initialize = () => {
        setId('');
        setTitle('');
        setContact('');
        setPrice('');
        if(setUpdateAdId){
            setUpdateAdId('');
        }
    }

    const putSingle = async (e) => {
        e.preventDefault();
        setId(Number(id));

        const updatedAd = {
            'title': title,
            'contact': contact,
            'price': price,
        };
        updateSingle(id, updatedAd, addMessage);
        if (setAdChanged) {
            setAdChanged(id);
        }      
        Initialize();
    }
    useEffect( () => {
        if (ad){
            setId(ad.id);
            setTitle(ad.title);
            setContact(ad.contact);
            setPrice(ad.price);
        }
    }, [ad])


    return html`
        <div class=${view === 'api' ? 'card card-api-fiori shadow-lg' : 'card card-fiori shadow-lg'}  >
            <form onSubmit=${putSingle} >
                <div class="card-body">
                    ${view === 'api' ? 
                        html`
                            <div class="card-title" >Update ad</div>
                            <input class="ad-input" type='text' value=${id} onChange=${(e) => { setId(e.target.value) }} placeholder='enter id!' ></input>
                            <input class="ad-input" type='text' value=${title} placeholder="add title!" onChange=${(e) => { setTitle(e.target.value) }} ></input>
                            <input class="ad-input" type='text' value=${contact} placeholder="add contact" onChange=${(e) => { setContact(e.target.value) }} ></input>
                            <input class="ad-input" type='text' value=${price} placeholder="add price" onChange=${(e) => { setPrice(e.target.value) }} ></input>
                            ` 
                        : html`
                            <input class="card-title" style="width: 8rem" type='text' value=${title} onChange=${(e) => { setTitle(e.target.value) }} ></input>
                            <input class="ad-input" type='text' value=${contact}  onChange=${(e) => { setContact(e.target.value) }} ></input>
                            <span class='badge bg-danger rounded-pill' >
                                <input class="ad-input" type='text' value=${price} onChange=${(e) => { setPrice(e.target.value) }} ></input>
                            </span>
                    `}
                    <div>
                        <button class="btn btn-primary api-button" >${view === 'api' ? 'PUT' : 'Submit'}</button>
                    </div>
                </div>
            </form>
        </div>
        `;
};