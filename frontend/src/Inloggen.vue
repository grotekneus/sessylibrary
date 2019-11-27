<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div class="container" style="margin-top: 10px;">
        <h3><i class="fas fa-user"></i>&nbsp;Inloggen</h3>

        <span v-if="$global.loginuser.picid !== -1">
            <h4 >
                U bent ingelogd als {{ $global.loginuser.name }}.
            </h4>

            <p>
                Welkom! <br/><br/>
                <img :src="$global.loginuser.portrait" />
            </p>

            <b-button @click="logout" variant="primary">Logout</b-button>
        </span>

        <b-card-group deck v-if="$global.loginuser.picid === -1">
            <b-card class="boek mb-3"
                    border-variant="dark"
                    title="Als Verstokte lezer"
                    img-src="https://picsum.photos/600/300/?image=1010"
                    img-alt="Image"
                    img-top
                    tag="article">
                <b-card-text>
                    Als <em>verstokte lezer</em> brengt u al uw boeken twee weken op voorhand braafjes terug naar de lokale bibliotheek.
                    Het uitlenen van boeken is toegestaan, meer nog: uw favoriete bibliothecaris holt u achterna om nog meer boeken uit te lenen.<br/>
                    Je leest ze immers toch in één dag uit!
                </b-card-text>

                <template v-slot:footer>
                    <b-button @click="loginVerstokteLezer" variant="primary">Login!</b-button>
                </template>
            </b-card>
            <b-card class="boek mb-3"
                    border-variant="dark"
                    title="Als Slechte Uitlener"
                    img-src="https://picsum.photos/600/300/?image=1014"
                    img-alt="Image"
                    img-top
                    tag="article">
                <b-card-text>
                    Als <em>slechte uitlener</em> brengt u al uw boeken steeds te laat binnen, of vergeet u ze helemaal. De lokale
                    bibliothecaris ergert zich dood aan u en zucht zodra u de bib binnen stapt. De kans dat je suggesties kan uitlenen
                    is héél gering.
                </b-card-text>

                <template v-slot:footer>
                    <b-button @click="loginSlechteUitlener" variant="primary">Login!</b-button>
                </template>
            </b-card>
        </b-card-group>

    </div>

</template>

<script>
    import axios from "axios"
    import { handle } from './errorhandling.js'

    export default {
        data() {

            return {
            }
        },
        name: 'inloggen',
        methods: {
            login(url, userToLogin) {
                axios.get(url)
                    .then(response => {
                        // if 200 OK then logged in
                        this.$global.loginuser.name = userToLogin.name
                        this.$global.loginuser.picid = userToLogin.picid
                        this.$global.loginuser.portrait = `https://picsum.photos/600/300/?image=${userToLogin.picid}`
                    }).catch(err => handle(this, err))
            },
            logout() {
                axios.get('/api/logout')
                    .then(response => {
                        this.$global.loginuser.portrait = ''
                        this.$global.loginuser.name = 'Anoniempje'
                        this.$global.loginuser.picid = -1
                    }).catch(err => handle(this, err))
            },
            loginVerstokteLezer() {
                this.login('/api/login-bad', { name: 'verstokte lezer', picid: 1010})
            },
            loginSlechteUitlener() {
                this.login('/api/login-spotless', { name: 'slechte uitlener', picid: 1014})
            }

        },
        components: {
        }
    }
</script>

<style>
</style>