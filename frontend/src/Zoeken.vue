<template>
    <div>

        <div class="zoekcontainer">
            <h3><i class="fas fa-search"></i>&nbsp;Zoeken</h3>
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

        <div class="resultcontainer" v-if="results.length > 0">
            <hr/>
            <h3><i class="fas fa-list"></i>&nbsp;Resultaten</h3>

            <div class="container">
                <div v-for="boekengroep in resultSplitByCardGroups()" v-bind:key="boekengroep.id">
                    <b-card-group deck>
                        <b-card class="boek mb-2"
                                border-variant="dark"
                                v-for="boek in boekengroep.boeken"
                                v-bind:key="boek.isbn"
                                :header="boek.author"
                                :title="boek.title"
                                :img-src="boek.thumbnail"
                                :img-alt="boek.title"
                                img-top
                                tag="article"
                                style="max-width: 20rem;">
                            <b-card-text>
                                {{ boek.description }}
                            </b-card-text>

                            <b-button :href="boek.url" variant="primary">Naar detail</b-button>
                        </b-card>
                    </b-card-group>
                </div>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
    const exampleBoek = (isbn) => {
        return {
            isbn,
            title: "Daarheen en weer terug",
            author: "Bilbo Baggings",
            thumbnail: "bla.png",
            description: "One ring to rule them all!",
            url: `/#/detail/${isbn}`
        }
    }

    export default {
        data() {
            return {
                form: {
                    zoekterm: ''
                },
                results: [exampleBoek(1), exampleBoek(2), exampleBoek(3), exampleBoek(4), exampleBoek(5), exampleBoek(6), exampleBoek(7), exampleBoek(8)],
                resultSplitByCardGroups: () => {
                    let tosplit = [...this.results]
                    let splitresult = []
                    let i = 0
                    while(tosplit.length > 0) {
                        splitresult.push({ id: `boeken${i}`, boeken: tosplit.splice(0, 3)})
                        i++
                    }
                    return splitresult
                }
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

    .resultcontainer {
        margin-top: 30px;
        margin-bottom: 30px;
        padding: 30px;
    }

    .resultcontainer h3 {
        padding-bottom: 20px;
    }

    .zoekcontainer {
        margin-top: 30px;
        margin-bottom: 30px;
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