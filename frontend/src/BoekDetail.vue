<template>

    <div>
        <b-card no-body>
            <b-tabs pills card>
                <b-tab title="Boek Detail" active>
                    <div class="container boekdetail">
                        <h3>Boek detail: ISBN {{ isbn }}</h3>

                    <b-card v-if="loaded"
                            :title="boek.title"
                            :sub-title="boek.subtitle"
                            :img-src="boek.thumbnail"
                            img-alt="thumbnail image" img-left>
                        <b-card-text>
                            {{ boek.text }}
                        </b-card-text>

                        <template v-slot:footer>
                            <b-button @click="leenUit" variant="primary" v-if="!boek.borrowed">
                                Uitlenen!
                            </b-button>
                            <span v-if="boek.borrowed">
                                <strong>Helaas!</strong> Dit boek is momenteel uitgeleend. <br/>
                                <b-button @click="geefTerug" variant="primary">
                                    Terugbrengen?
                                </b-button>
                            </span>
                        </template>
                    </b-card>


                    </div>
                </b-tab>
                <b-tab title="Terug naar Zoeken" @click="goBack" />
            </b-tabs>
        </b-card>
    </div>

</template>

<script>
    import axios from "axios"
    import { handle } from './errorhandling.js'

    const setBoekParams = (boekUI, boek) => {
        boekUI.title = `Titel: ${boek.title}`
        boekUI.thumbnail = boek.thumbnail
        boekUI.subtitle = `Door ${boek.author}`
        boekUI.text = `${boek.description}`
        boekUI.borrowed = boek.borrowed === "true" ? true : false
    }

    export default {
        data() {
            return {
                isbn: this.$route.params.isbn,
                loaded: false,
                boek: {
                    borrowed: false
                }
            }
        },
        name: 'boekdetail',
        methods: {
            geefTerug() {
                axios.get(`/api/bring-back?isbn=${this.isbn}`)
                    .then(response => {
                        this.boek.borrowed = false
                        this.$bvToast.toast(`Bedankt voor je stiptheid met het terugbrengen!`, {
                            title: `Terugbrengen OK!`,
                            variant: 'success',
                            solid: true
                        })
                    }).catch(err => handle(this, err))
            },
            leenUit() {
                axios.get(`/api/borrow?isbn=${this.isbn}`)
                    .then(response => {
                        this.boek.borrowed = true
                        this.$bvToast.toast(`Proficiat, veel leesplezier met ${this.boek.title}`, {
                            title: `Uitlening OK!`,
                            variant: 'success',
                            solid: true
                        })
                    }).catch(err => {
                        if(err && err.response && err.response.status == 403) {
                            this.$bvToast.toast(`U bent niet gemachtigd om dit boek uit te lenen. Betaal dringend je factuur!`, {
                                title: `Uitlening NIET gelukt`,
                                variant: 'warning',
                                solid: true
                            })
                        } else {
                            handle(this, err)
                        }
                })
            },
            getBookDetail() {
                axios.get(`/api/get-book?isbn=${this.isbn}`)
                    .then(response => {
                        setBoekParams(this.boek, response.data)
                        this.loaded = true
                    }).catch(err => handle(this, err))
            },
            goBack() {
                window.location.href = "/#/"
            }
        },
        beforeMount() {
            this.getBookDetail()
        },
        components: {
        }
    }
</script>

<style>
    .boekdetail .card-text {
        padding: 20px;
    }

    .card-header {
        background-color: #EEEEEE !important;
    }
    .card-body {
        padding: 0 !important;
    }

    .boekdetail {
        padding-top: 20px;
        padding-bottom: 20px;
    }


</style>

