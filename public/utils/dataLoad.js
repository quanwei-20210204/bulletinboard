'use strict';

import postAd from '../components/create/postAd.js';

export default function dataLoad() {
    const adData = [
        { title: 'Bus', contact: 'Mark', price: '12.000' },
        { title: 'Fast Car', contact: 'Marcel', price: '35.000' },
        { title: '100 Books', contact: 'Michael', price: '45' },
        { title: 'Shoes', contact: 'Anke', price: '12' },
        { title: 'Lawn Mower', contact: 'Gudrun', price: '350' },
        { title: 'Football', contact: 'Jens', price: '5' }
    ];
    
    adData.forEach( ad => {
        postAd(ad)
    })
};