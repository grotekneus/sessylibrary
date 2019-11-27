
export function handle(me, err, call) {
    const errMsg = err.response ? `Response: ${err.response.data}` : `Stack: ${err.stack}`
    const errUrl = err.config ? err.config.url : 'internal JS code'

    me.$bvToast.toast(`${err} - ${errMsg}`, {
        title: `Error calling '${errUrl}'`,
        variant: 'danger',
        solid: true,
        'no-auto-hide': true,
        message: err.response.data
    })

    if(console && console.log) {
        console.log(`Error calling ${errUrl} - ${errMsg}`)
    }
}

export default {
    handle
}
