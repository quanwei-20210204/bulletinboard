'use strict';
import {html, render, useState} from 'https://unpkg.com/htm@3.1.0/preact/standalone.module.js';

import Api from './api.js';
import BulletinBoard from './bulletin-board.js';

// Main App rednering Bulletin Board or API View
const App = () => {
    const [toggle, setToggle] = useState('board');
    const handleClick = () => {
        if (toggle === 'api') {
            setToggle('board');
        } else {
            setToggle('api');
        }
    };
    return html`
            <main class="bg-fiori font-monospace vh-100" >
                <div  class="d-inline-flex" >
                    <img class="sap-img" src="./SAP_R_grad.svg" />
                    <h5 class="text-fiori" >${toggle === 'api' ? 'Api View' : 'Bulletin Board'}</h5>
                    <button type="button" 
                        class="btn btn-outline-light position-absolute top-0 end-0 view-button" 
                        onClick=${handleClick} >${toggle === 'api' ? 'Bulletin Board' : 'Api View'}
                    </button>
                    </div>
                        ${toggle === 'api' ? html`<${Api} view=${toggle} />` : html`<${BulletinBoard} view=${toggle} />`}
                    </div>
            `;
};
render(html`<${App}/>`, document.getElementById('app'));