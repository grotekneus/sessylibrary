<template>
    <div>

        <div class="zoekcontainer">
            <h3><i class="fas fa-question-circle"></i>&nbsp;Zoeken</h3>
            <p>&nbsp;</p>

            <div class="container">
                Zoek in catalogus<br/>
                <b-form @submit="onSubmit" inline>
                    <b-form-input
                            v-model="form.zoekterm"
                            required
                            id="searchterm"
                            class="mb-2 mr-sm-2 mb-sm-0"
                            placeholder="bvb. 'Lord of the Rings'"
                    ></b-form-input>
                    <b-button type="submit" variant="primary">Zoek!</b-button>
                </b-form>
            </div>

            <h3>&nbsp;</h3>
        </div>

        <div class="container resultcontainer">
            {{ results }}
        </div>

    </div>
</template>

<script>
    export default {
        data() {
            return {
                form: {
                    zoekterm: ''
                },
                results: []
            }
        },
        name: 'zoeken',
        methods: {
            onSubmit(evt) {
               evt.preventDefault()
                this.results.push(this.form.zoekterm)
            }
        },
        components: {
        }
    }
</script>

<style>
    form {
        padding-top:10px;
    }

    form input {
        width: 88% !important;
    }

    form button {
        width: 10% !important;
    }

    .zoekcontainer {
        margin-top: 30px;
        marign-bottom: 30px;
        padding: 30px;
        background-image: url('https://brainbaking.com//img/2017inbooks.png');
    }

    .zoekcontainer .container {
        background-color: #EEEEEE;
        border-radius: 10px;
        padding: 10px;
    }

    .zoekcontainer h3 {
        color: white;
    }

</style>