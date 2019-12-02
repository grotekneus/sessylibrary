<template>
    <div id="app">

        <nav class="navbar sticky-top navbar-expand-lg navbar-light bg-light">
            <router-link to="/">
        <span class="navbar-brand mb-0 h1">
            <img :src="'https://picsum.photos/id/' + $global.loginuser.picid + '/100/100'"
                 style="position: absolute; right: 20px; border: 1px solid black;"
                 :alt="'Gebruiker ' + $global.loginuser.name"
            />
          <i class="fas fa-book-reader"></i>&nbsp;
          Welkom bij de 'SESsy Library', {{ $global.loginuser.name }}
        </span>
            </router-link>
            <span class="navbar-nav mr-auto">
        &nbsp;
            </span>
        </nav>

        <router-view></router-view>

        <hr style="clear: both;" />

        <footer class="page-footer bg-light font-small pt-4">
            <div class="container">
                <div class="row">
                    <div class="footer-copyright text-center col-sm">
                        SESsy Library
                        <hr/>
                    </div>
                </div>
                <div class="row">
                    <div class="footer-copyright text-center py-3 col-sm">
                        <strong>Adres</strong><br/><br/>
                        Boekenlaan 1<br/>
                        3500 Hasselt<br/>
                        <a href="https://maps.google.be" target="_blank">Toon op kaart</a>
                    </div>
                    <div class="footer-copyright text-center py-3 col-sm">
                        <strong>Contactgegevens</strong><br/><br/>
                        011 12 34 56<br/>
                        <a href="mailto:info@sessylibrary.be">info@sessylibrary.be</a>
                    </div>
                    <div class="footer-copyright text-center py-3 col-sm">
                        <strong>Project gesteund door</strong><br/><br/>
                        KU Leuven<br/>
                        UHasselt
                    </div>
                </div>
                <div class="row">
                    <div class="footer-copyright text-center col-sm">
                        <hr/>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</template>

<script>
    export default {
        data() {
            return {}
        },
        name: 'app',
        methods: {
            scrollToTop: () => window.scrollTo(0, 0),
        scrollToBottom: () => window.scrollTo(0, document.body.scrollHeight)
    }
    }
</script>

<style>
    #app {
        font-family: 'Roboto', sans-serif;
    }

    nav {
        border-bottom: 1px solid lightgrey;
    }

    footer {
        border-top: 1px solid lightgrey;
        border-bottom: 1px solid lightgrey;
        padding-bottom: 25px;
    }

    footer .footer-copyright {
        padding-top: 0 !important;
        padding-bottom: 0 !important;
    }

</style>