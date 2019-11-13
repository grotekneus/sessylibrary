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
    }

    export default {
        data() {
            return {
                isbn: this.$route.params.isbn,
                loaded: false,
                boek: {}
            }
        },
        name: 'boekdetail',
        methods: {
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

