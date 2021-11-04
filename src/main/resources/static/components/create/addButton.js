'use strict';
import {html} from 'https://unpkg.com/htm@3.1.0/preact/standalone.module.js';

// add Button
export default function AddButton( {setNewAd} ) {
    const handleClick = (e) => {
        setNewAd(true);
    };
    return html`
        <div class="card card-fiori opacity-50 shadow-lg" onClick=${handleClick} >
            <div class="text-dark" >
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-lg" viewBox="0 0 16 16">
                    <path d="M8 0a1 1 0 0 1 1 1v6h6a1 1 0 1 1 0 2H9v6a1 1 0 1 1-2 0V9H1a1 1 0 0 1 0-2h6V1a1 1 0 0 1 1-1z"/>
                </svg>...
            </div>
        </div>
    `
};