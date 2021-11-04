'use strict';
import {html, useState} from 'https://unpkg.com/htm@3.1.0/preact/standalone.module.js';

import DisplayAllAdsAPI from './components/display/displayAllAdsAPI.js';
import DisplaySingleAd from './components/display/displaySingleAd.js';
import DeleteAd from './components/delete/deleteAd.js';
import CreateAd from './components/create/createAd.js';
import UpdateAd from './components/update/updateAd.js';
import LogMessages from './logging/logMessages.js';

// Api View 
export default function Api ( {view} ) {
    const [messages, setMessages] = useState([]);
    const addMessage = (method, status, text, url, date) => {
        const newMessage = { method: method, status: status, text: text, url: url, date: date };
        setMessages((prev) => [newMessage, ...prev]);
    };

return html`
        <div class="container" >
            <div class='row row-col-auto'>
                <${DisplaySingleAd} addMessage=${addMessage} />
                <${CreateAd}  view=${view} addMessage=${addMessage} />
                <${UpdateAd}  view=${view} addMessage=${addMessage} />
                <${DeleteAd} addMessage=${addMessage} />
            </div>
        </div>
        <div class="container">
            <div class='row row-col-auto'>
                <${DisplayAllAdsAPI}  addMessage=${addMessage} />
                <${LogMessages} messages=${messages} />
            </div>
        </div>
        `;
};
