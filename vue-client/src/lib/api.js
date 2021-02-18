const endpoint = 'http://localhost:7080/api/carInsurance';

export const put = async (data) => {
    const response = await fetch(endpoint, {
        method: 'PUT',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json',
        },
        dataType: 'json',
        referrerPolicy: 'no-referrer',
        body: JSON.stringify(data),
    });

    return {
        status: response.status,
        data: response.status > 201 ? null : await response.json(),
    }
}
