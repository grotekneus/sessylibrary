
export function handle(me, err, call) {
    const errMsg = err.response ? `Response: ${err.response.data}` : `Stack: ${err.stack}`

    me.$bvToast.toast(`${err} - ${errMsg}`, {
        title: `Error calling '${err.config.url}'`,
        variant: 'danger',
        solid: true,
        'no-auto-hide': true,
        message: err.response.data
    })

    if(console && console.error) {
        console.error(err)
    }
}

export default {
    handle
}
