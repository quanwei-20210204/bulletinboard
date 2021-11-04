'use strict';

export default function formatedDate () {
    const dateOptions = {
        'weekday': 'short',
        'year': 'numeric',
        'month': 'short',
        'day': 'numeric',
        'hour': '2-digit',
        'minute': '2-digit',
        'second': '2-digit'
    };
    let newDate = new Date(Date()).toLocaleString(undefined, dateOptions);
    return newDate; 
};