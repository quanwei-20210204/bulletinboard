'use strict';
import {html, useState} from 'https://unpkg.com/htm@3.1.0/preact/standalone.module.js';

import DisplayAd from './displayAd.js';
import formatedDate from '../../utils/formatedDate.js';


export default function DisplaySingleAd({ addMessage }) {
    const [id, setId] = useState('');
    const [ad, setAd] = useState({});

    const getSingle = async (e) => {
        e.preventDefault();
        setId(Number(id));
        if (!id) {
            addMessage('GET', 404, 'No id submitted', '');
            setId('');
            return
        };

        try {
            const response = await fetch(`/api/v1/ads/${id}`, {
                headers: { 'content-type': 'application/json' },
                method: 'GET'
            })
            const jsonResponse = await response.json();
            setAd(jsonResponse);
            setId('');
            addMessage('GET', response.status, response.statusText, response.url, formatedDate());
        } catch (err) {
            addMessage('GET', response.status, response.statusText, response.url);
            setId('');
            setAd({});
        }
    };

    return html`
        <div class="card card-api-fiori shadow-lg" >
            <div class="card-body">
                <div class="card-title" >Display single ad
                    <form onSubmit=${getSingle} >
                        <div class="m-2">
                            <input class="input" type='text' placeholder='enter id' value=${id} onChange=${(e) => { setId(e.target.value) }} ></input>
                        </div>
                        <div class="m-2">
                            <button class="btn btn-primary api-button" >GET</button>
                        </div>
                            <ol class="list-group list-group-numbered">
                                ${ad ? html`<${DisplayAd} ad=${ad} />` : ''}
                            </ol>
                    </form>
                </div>
            </div>
        </div>
            `;
};