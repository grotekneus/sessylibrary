<template>

    <div>
        <b-card no-body>
            <b-tabs pills card>
                <b-tab title="Boeken Zoeken" active>
                    <b-card-text>
                        <Zoeken />
                    </b-card-text>
                </b-tab>
                <b-tab title="Uitleentermijn Verlengen">
                    <b-card-text>
                        <Verlengen />
                    </b-card-text>
                </b-tab>
                <b-tab title="Openingsuren">
                    <b-card-text>
                        <Openingsuren />
                    </b-card-text>
                </b-tab>
            </b-tabs>
        </b-card>
    </div>

</template>

<script>
    import Zoeken from './Zoeken.vue'
    import Verlengen from './Verlengen.vue'
    import Openingsuren from './Openingsuren.vue'

    export default {
        data() {

            return {
            }
        },
        name: 'home',
        methods: {
        },
        components: {
            Zoeken,
            Verlengen,
            Openingsuren
        }
    }
</script>

<style>
    .card-header {
        background-color: #EEEEEE !important;
    }
    .card-body {
        padding: 0 !important;
    }
</style>
