'use strict';
import {html, useState} from 'https://unpkg.com/htm@3.1.0/preact/standalone.module.js';

import deleteSingle from './deleteSingle.js';


export default function DeleteAd( { addMessage } ) {
    const [id, setId] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!id) {
            addMessage('DELETE', 404, 'No id submitted', '');
            return
        };
        setId(Number(id));
        deleteSingle(id, setId, addMessage);
        setId('');
    };

    return html`
        <div class="card card-api-fiori shadow-lg" >
            <div class="card-body">
                <div class="card-title" >Delete single ad
                    <form onSubmit=${handleSubmit} >
                        <div class="m-2">
                            <input class="input" type='text' placeholder='enter id' value=${id} onChange=${(e) => { setId(e.target.value) }} ></input>
                        </div>
                        <div class="m-2">
                            <button class="btn btn-primary api-button">DELETE</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        `;
};