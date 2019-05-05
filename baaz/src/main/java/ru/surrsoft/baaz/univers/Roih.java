package ru.surrsoft.baaz.univers;

import android.graphics.Color;
import android.graphics.Typeface;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;

import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.widgets2.BuilderTV;
import ru.surrsoft.baaz.widgets2.buLay.BuLayLinear_01;
import ru.surrsoft.baaz.widgets2.nviews.NLinearLayout;

/**
 * ПОНЯТИЯ
 * <li> [[maep]] - имя шрифта; это имя константы хранящей путь к шрифту </li>
 * <p>
 * [[roih]]
 */
public class Roih {
    //=== [maep]s
    //первые три символа константы "TF_" значимы - используются рефлексией

    //CYR
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public static String TF_CYR_OLD_STANDARD_REGULAR = "fonts/Old_Standard_TT/OldStandard-Regular.ttf";
    public static String TF_CYR_OLD_STANDARD_ITALIC = "fonts/Old_Standard_TT/OldStandard-Italic.ttf";
    public static String TF_CYR_OLD_STANDARD_BOLD = "fonts/Old_Standard_TT/OldStandard-Bold.ttf";

    public static String TF_CYR_PHILOSOPHER_REGULAR = "fonts/Philosopher/Philosopher-Regular.ttf";
    public static String TF_CYR_PHILOSOPHER_ITALIC = "fonts/Philosopher/Philosopher-Italic.ttf";
    public static String TF_CYR_PHILOSOPHER_BOLD_ITALIC = "fonts/Philosopher/Philosopher-BoldItalic.ttf";
    public static String TF_CYR_PHILOSOPHER_BOLD = "fonts/Philosopher/Philosopher-Bold.ttf";

    public static String TF_CYR_PLAYFAIR_DISPLAY_SC_REGULAR = "fonts/Playfair_Display_SC/PlayfairDisplaySC-Regular.ttf";
    public static String TF_CYR_PLAYFAIR_DISPLAY_SC_ITALIC = "fonts/Playfair_Display_SC/PlayfairDisplaySC-Italic.ttf";
    public static String TF_CYR_PLAYFAIR_DISPLAY_SC_BOLD_ITALIC = "fonts/Playfair_Display_SC/PlayfairDisplaySC-BoldItalic.ttf";
    public static String TF_CYR_PLAYFAIR_DISPLAY_SC_BOLD = "fonts/Playfair_Display_SC/PlayfairDisplaySC-Bold.ttf";
    public static String TF_CYR_PLAYFAIR_DISPLAY_SC_BLACK_ITALIC = "fonts/Playfair_Display_SC/PlayfairDisplaySC-BlackItalic.ttf";
    public static String TF_CYR_PLAYFAIR_DISPLAY_SC_BLACK = "fonts/Playfair_Display_SC/PlayfairDisplaySC-Black.ttf";

    public static String TF_CYR_CORMORANT_GARAMOND_SEMI_BOLD_ITALIC = "fonts/Cormorant_Garamond/CormorantGaramond-SemiBoldItalic.ttf";
    public static String TF_CYR_CORMORANT_GARAMOND_SEMI_BOLD = "fonts/Cormorant_Garamond/CormorantGaramond-SemiBold.ttf";
    public static String TF_CYR_CORMORANT_GARAMOND_REGULAR = "fonts/Cormorant_Garamond/CormorantGaramond-Regular.ttf";
    public static String TF_CYR_CORMORANT_GARAMOND_MEDIUM_ITALIC = "fonts/Cormorant_Garamond/CormorantGaramond-MediumItalic.ttf";
    public static String TF_CYR_CORMORANT_GARAMOND_MEDIUM = "fonts/Cormorant_Garamond/CormorantGaramond-Medium.ttf";
    public static String TF_CYR_CORMORANT_GARAMOND_LIGHT_ITALIC = "fonts/Cormorant_Garamond/CormorantGaramond-LightItalic.ttf";
    public static String TF_CYR_CORMORANT_GARAMOND_LIGHT = "fonts/Cormorant_Garamond/CormorantGaramond-Light.ttf";
    public static String TF_CYR_CORMORANT_GARAMOND_ITALIC = "fonts/Cormorant_Garamond/CormorantGaramond-Italic.ttf";
    public static String TF_CYR_CORMORANT_GARAMOND_BOLD_ITALIC = "fonts/Cormorant_Garamond/CormorantGaramond-BoldItalic.ttf";
    public static String TF_CYR_CORMORANT_GARAMOND_BOLD = "fonts/Cormorant_Garamond/CormorantGaramond-Bold.ttf";

    public static String TF_CYR_CORMORANT_SEMI_BOLD_ITALIC = "fonts/Cormorant/Cormorant-SemiBoldItalic.ttf";
    public static String TF_CYR_CORMORANT_SEMI_BOLD = "fonts/Cormorant/Cormorant-SemiBold.ttf";
    public static String TF_CYR_CORMORANT_REGULAR = "fonts/Cormorant/Cormorant-Regular.ttf";
    public static String TF_CYR_CORMORANT_MEDIUM_ITALIC = "fonts/Cormorant/Cormorant-MediumItalic.ttf";
    public static String TF_CYR_CORMORANT_MEDIUM = "fonts/Cormorant/Cormorant-Medium.ttf";
    public static String TF_CYR_CORMORANT_LIGHT_ITALIC = "fonts/Cormorant/Cormorant-LightItalic.ttf";
    public static String TF_CYR_CORMORANT_LIGHT = "fonts/Cormorant/Cormorant-Light.ttf";
    public static String TF_CYR_CORMORANT_ITALIC = "fonts/Cormorant/Cormorant-Italic.ttf";
    public static String TF_CYR_CORMORANT_BOLD_ITALIC = "fonts/Cormorant/Cormorant-BoldItalic.ttf";
    public static String TF_CYR_CORMORANT_BOLD = "fonts/Cormorant/Cormorant-Bold.ttf";

    public static String TF_CYR_ARSENAL_REGULAR = "fonts/Arsenal/Arsenal-Regular.ttf";
    public static String TF_CYR_ARSENAL_ITALIC = "fonts/Arsenal/Arsenal-Italic.ttf";
    public static String TF_CYR_ARSENAL_BOLD_ITALIC = "fonts/Arsenal/Arsenal-BoldItalic.ttf";
    public static String TF_CYR_ARSENAL_BOLD = "fonts/Arsenal/Arsenal-Bold.ttf";

    public static String TF_CYR_ANONYMOUS_PRO_REGULAR = "fonts/Anonymous_Pro/AnonymousPro-Regular.ttf";
    public static String TF_CYR_ANONYMOUS_PRO_ITALIC = "fonts/Anonymous_Pro/AnonymousPro-Italic.ttf";
    public static String TF_CYR_ANONYMOUS_PRO_BOLD_ITALIC = "fonts/Anonymous_Pro/AnonymousPro-BoldItalic.ttf";
    public static String TF_CYR_ANONYMOUS_PRO_BOLD = "fonts/Anonymous_Pro/AnonymousPro-Bold.ttf";

    public static String TF_CYR_ROBOTO_THIN_ITALIC = "fonts/Roboto/Roboto_ThinItalic.ttf";
    public static String TF_CYR_ROBOTO_REGULAR = "fonts/Roboto/Roboto_Regular.ttf";
    public static String TF_CYR_ROBOTO_MEDIUM = "fonts/Roboto/Roboto_Medium.ttf";
    public static String TF_CYR_ROBOTO_LIGHT = "fonts/Roboto/Roboto_Light.ttf";
    public static String TF_CYR_ROBOTO_CONDENSED = "fonts/Roboto/Roboto_Condensed.ttf";
    public static String TF_CYR_ROBOTO_BOLDITALIC = "fonts/Roboto/Roboto_Bolditalic.ttf";

    public static String TF_CYR_PLAY_BOLD = "fonts/Play/Play-Bold.ttf";
    public static String TF_CYR_PLAY_REGULAR = "fonts/Play/Play-Regular.ttf";

    public static String TF_CYR_ISTOK_WEB_BOLD_ITALIC = "fonts/IstokWeb/IstokWeb-BoldItalic.ttf";
    public static String TF_CYR_ISTOK_WEB_BOLD = "fonts/IstokWeb/IstokWeb-Bold.ttf";
    public static String TF_CYR_ISTOK_WEB_ITALIC = "fonts/IstokWeb/IstokWeb-Italic.ttf";
    public static String TF_CYR_ISTOK_WEB_REGULAR = "fonts/IstokWeb/IstokWeb-Regular.ttf";

    public static String TF_CYR_ARIMO_BOLD_ITALIC = "fonts/Arimo/Arimo-BoldItalic.ttf";
    public static String TF_CYR_ARIMO_BOLD = "fonts/Arimo/Arimo-Bold.ttf";
    public static String TF_CYR_ARIMO_ITALIC = "fonts/Arimo/Arimo-Italic.ttf";
    public static String TF_CYR_ARIMO_REGULAR = "fonts/Arimo/Arimo-Regular.ttf";

    public static String TF_CYR_COMFORTAA_BOLD = "fonts/Comfortaa/Comfortaa-Bold.ttf";
    public static String TF_CYR_COMFORTAA_LIGHT = "fonts/Comfortaa/Comfortaa-Light.ttf";
    public static String TF_CYR_COMFORTAA_REGULAR = "fonts/Comfortaa/Comfortaa-Regular.ttf";

    public static String TF_CYR_NORD_BOLD = "fonts/Nord/NordBold.ttf";
    public static String TF_CYR_NORD_LIGHT = "fonts/Nord/NordLight.ttf";
    public static String TF_CYR_NORD_MEDIUM = "fonts/Nord/NordMedium.ttf";
    public static String TF_CYR_NORD_REGULAR = "fonts/Nord/NordRegular.ttf";

    //public static String TF_CYR_PROBAPRO_ITALIC = "fonts/Probapro/probapro-italic.ttf";
    //public static String TF_CYR_PROBAPRO_REGULAR = "fonts/Probapro/probapro-regular.ttf";

    //public static String TF_CYR_FRENCHPRESS = "fonts/Frenchpress/Frenchpress_freefont.ttf";
    //public static String TF_CYR_MATEUR = "fonts/Mateur/Mateur.ttf";
    //public static String TF_CYR_SANGHA = "fonts/Sangha/sangha.ttf";

    //одиночки
    public static String TF_CYR_POIRET_ONE_REGULAR = "fonts/Poiret_One/PoiretOne-Regular.ttf";
    public static String TF_CYR_BAD_SCRIPT_REGULAR = "fonts/Bad_Script/BadScript-Regular.ttf";
    public static String TF_CYR_ALICE_REGULAR = "fonts/Alice/Alice-Regular.ttf";
    public static String TF_CYR_KURALE_REGULAR = "fonts/Kurale/Kurale-Regular.ttf";
    public static String TF_CYR_FORUM_REGULAR = "fonts/Forum/Forum-Regular.ttf";
    public static String TF_CYR_DIDACT_GOTHIC = "fonts/DidactGothic/DidactGothic.ttf";
    public static String TF_CYR_ANDIKA_R = "fonts/Andika/Andika-R.ttf";
    public static String TF_CYR_MONTSERRAT_REGULAR = "fonts/Montserrat/Montserrat-Regular.ttf";
    public static String TF_CYR_SEGOEUIL = "fonts/Segoeuil/Segoeuil.ttf";



    //ENG
    //``````````````````````````````````````````````````````````````````````````````````````````````
    public static String TF_ENG_ALEGREYA_SANS_SC_BLACK = "fonts/ENG_Alegreya_Sans_SC/AlegreyaSansSC-Black.ttf";
    public static String TF_ENG_ALEGREYA_SANS_SC_BLACK_ITALIC = "fonts/ENG_Alegreya_Sans_SC/AlegreyaSansSC-BlackItalic.ttf";
    public static String TF_ENG_ALEGREYA_SANS_SC_BOLD = "fonts/ENG_Alegreya_Sans_SC/AlegreyaSansSC-Bold.ttf";
    public static String TF_ENG_ALEGREYA_SANS_SC_BOLD_ITALIC = "fonts/ENG_Alegreya_Sans_SC/AlegreyaSansSC-BoldItalic.ttf";
    public static String TF_ENG_ALEGREYA_SANS_SC_EXTRA_BOLD = "fonts/ENG_Alegreya_Sans_SC/AlegreyaSansSC-ExtraBold.ttf";
    public static String TF_ENG_ALEGREYA_SANS_SC_EXTRA_BOLD_ITALIC = "fonts/ENG_Alegreya_Sans_SC/AlegreyaSansSC-ExtraBoldItalic.ttf";
    public static String TF_ENG_ALEGREYA_SANS_SC_ITALIC = "fonts/ENG_Alegreya_Sans_SC/AlegreyaSansSC-Italic.ttf";
    public static String TF_ENG_ALEGREYA_SANS_SC_LIGHT = "fonts/ENG_Alegreya_Sans_SC/AlegreyaSansSC-Light.ttf";
    public static String TF_ENG_ALEGREYA_SANS_SC_LIGHT_ITALIC = "fonts/ENG_Alegreya_Sans_SC/AlegreyaSansSC-LightItalic.ttf";
    public static String TF_ENG_ALEGREYA_SANS_SC_MEDIUM = "fonts/ENG_Alegreya_Sans_SC/AlegreyaSansSC-Medium.ttf";
    public static String TF_ENG_ALEGREYA_SANS_SC_MEDIUM_ITALIC = "fonts/ENG_Alegreya_Sans_SC/AlegreyaSansSC-MediumItalic.ttf";
    public static String TF_ENG_ALEGREYA_SANS_SC_REGULAR = "fonts/ENG_Alegreya_Sans_SC/AlegreyaSansSC-Regular.ttf";
    public static String TF_ENG_ALEGREYA_SANS_SC_THIN = "fonts/ENG_Alegreya_Sans_SC/AlegreyaSansSC-Thin.ttf";
    public static String TF_ENG_ALEGREYA_SANS_SC_THIN_ITALIC = "fonts/ENG_Alegreya_Sans_SC/AlegreyaSansSC-ThinItalic.ttf";

    public static String TF_ENG_AMATIC_SC_BOLD = "fonts/ENG_Amatic_SC/AmaticSC-Bold.ttf";
    public static String TF_ENG_AMATIC_SC_REGULAR = "fonts/ENG_Amatic_SC/AmaticSC-Regular.ttf";

    public static String TF_ENG_ARCHITECTS_DAUGHTER = "fonts/ENG_Architects_Daughter/ArchitectsDaughter.ttf";
    public static String TF_ENG_BLACK_OPS_ONE_REGULAR = "fonts/ENG_Black_Ops_One/BlackOpsOne-Regular.ttf";

    public static String TF_ENG_CHIVO_BLACK = "fonts/ENG_Chivo/Chivo-Black.ttf";
    public static String TF_ENG_CHIVO_BLACK_ITALIC = "fonts/ENG_Chivo/Chivo-BlackItalic.ttf";
    public static String TF_ENG_CHIVO_BOLD = "fonts/ENG_Chivo/Chivo-Bold.ttf";
    public static String TF_ENG_CHIVO_BOLD_ITALIC = "fonts/ENG_Chivo/Chivo-BoldItalic.ttf";
    public static String TF_ENG_CHIVO_ITALIC = "fonts/ENG_Chivo/Chivo-Italic.ttf";
    public static String TF_ENG_CHIVO_LIGHT = "fonts/ENG_Chivo/Chivo-Light.ttf";
    public static String TF_ENG_CHIVO_LIGHT_ITALIC = "fonts/ENG_Chivo/Chivo-LightItalic.ttf";
    public static String TF_ENG_CHIVO_REGULAR = "fonts/ENG_Chivo/Chivo-Regular.ttf";

    public static String TF_ENG_DOSIS_BOLD = "fonts/ENG_Dosis/Dosis-Bold.ttf";
    public static String TF_ENG_DOSIS_EXTRA_BOLD = "fonts/ENG_Dosis/Dosis-ExtraBold.ttf";
    public static String TF_ENG_DOSIS_EXTRA_LIGHT = "fonts/ENG_Dosis/Dosis-ExtraLight.ttf";
    public static String TF_ENG_DOSIS_LIGHT = "fonts/ENG_Dosis/Dosis-Light.ttf";
    public static String TF_ENG_DOSIS_MEDIUM = "fonts/ENG_Dosis/Dosis-Medium.ttf";
    public static String TF_ENG_DOSIS_REGULAR = "fonts/ENG_Dosis/Dosis-Regular.ttf";
    public static String TF_ENG_DOSIS_SEMI_BOLD = "fonts/ENG_Dosis/Dosis-SemiBold.ttf";

    public static String TF_ENG_ECONOMICA_BOLD = "fonts/ENG_Economica/Economica-Bold.ttf";
    public static String TF_ENG_ECONOMICA_BOLD_ITALIC = "fonts/ENG_Economica/Economica-BoldItalic.ttf";
    public static String TF_ENG_ECONOMICA_ITALIC = "fonts/ENG_Economica/Economica-Italic.ttf";
    public static String TF_ENG_ECONOMICA_REGULAR = "fonts/ENG_Economica/Economica-Regular.ttf";

    public static String TF_ENG_EXO_BLACK = "fonts/ENG_Exo/Exo-Black.ttf";
    public static String TF_ENG_EXO_BLACK_ITALIC = "fonts/ENG_Exo/Exo-BlackItalic.ttf";
    public static String TF_ENG_EXO_BOLD = "fonts/ENG_Exo/Exo-Bold.ttf";
    public static String TF_ENG_EXO_BOLD_ITALIC = "fonts/ENG_Exo/Exo-BoldItalic.ttf";
    public static String TF_ENG_EXO_EXTRA_BOLD = "fonts/ENG_Exo/Exo-ExtraBold.ttf";
    public static String TF_ENG_EXO_EXTRA_BOLD_ITALIC = "fonts/ENG_Exo/Exo-ExtraBoldItalic.ttf";
    public static String TF_ENG_EXO_EXTRA_LIGHT = "fonts/ENG_Exo/Exo-ExtraLight.ttf";
    public static String TF_ENG_EXO_EXTRA_LIGHT_ITALIC = "fonts/ENG_Exo/Exo-ExtraLightItalic.ttf";
    public static String TF_ENG_EXO_ITALIC = "fonts/ENG_Exo/Exo-Italic.ttf";
    public static String TF_ENG_EXO_LIGHT = "fonts/ENG_Exo/Exo-Light.ttf";
    public static String TF_ENG_EXO_LIGHT_ITALIC = "fonts/ENG_Exo/Exo-LightItalic.ttf";
    public static String TF_ENG_EXO_MEDIUM = "fonts/ENG_Exo/Exo-Medium.ttf";
    public static String TF_ENG_EXO_MEDIUM_ITALIC = "fonts/ENG_Exo/Exo-MediumItalic.ttf";
    public static String TF_ENG_EXO_REGULAR = "fonts/ENG_Exo/Exo-Regular.ttf";
    public static String TF_ENG_EXO_SEMI_BOLD = "fonts/ENG_Exo/Exo-SemiBold.ttf";
    public static String TF_ENG_EXO_SEMI_BOLD_ITALIC = "fonts/ENG_Exo/Exo-SemiBoldItalic.ttf";
    public static String TF_ENG_EXO_THIN = "fonts/ENG_Exo/Exo-Thin.ttf";
    public static String TF_ENG_EXO_THIN_ITALIC = "fonts/ENG_Exo/Exo-ThinItalic.ttf";

    public static String TF_ENG_GLORIA_HALLELUJAH = "fonts/ENG_Gloria_Hallelujah/GloriaHallelujah.ttf";
    public static String TF_ENG_GRAND_HOTEL_REGULAR = "fonts/ENG_Grand_Hotel/GrandHotel-Regular.ttf";
    public static String TF_ENG_GRUPPO_REGULAR = "fonts/ENG_Gruppo/Gruppo-Regular.ttf";

    public static String TF_ENG_INCONSOLATA_BOLD = "fonts/ENG_Inconsolata/Inconsolata-Bold.ttf";
    public static String TF_ENG_INCONSOLATA_REGULAR = "fonts/ENG_Inconsolata/Inconsolata-Regular.ttf";

    public static String TF_ENG_JOSEFIN_SLAB_BOLD = "fonts/ENG_Josefin_Slab/JosefinSlab-Bold.ttf";
    public static String TF_ENG_JOSEFIN_SLAB_BOLD_ITALIC = "fonts/ENG_Josefin_Slab/JosefinSlab-BoldItalic.ttf";
    public static String TF_ENG_JOSEFIN_SLAB_ITALIC = "fonts/ENG_Josefin_Slab/JosefinSlab-Italic.ttf";
    public static String TF_ENG_JOSEFIN_SLAB_LIGHT = "fonts/ENG_Josefin_Slab/JosefinSlab-Light.ttf";
    public static String TF_ENG_JOSEFIN_SLAB_LIGHT_ITALIC = "fonts/ENG_Josefin_Slab/JosefinSlab-LightItalic.ttf";
    public static String TF_ENG_JOSEFIN_SLAB_REGULAR = "fonts/ENG_Josefin_Slab/JosefinSlab-Regular.ttf";
    public static String TF_ENG_JOSEFIN_SLAB_SEMI_BOLD = "fonts/ENG_Josefin_Slab/JosefinSlab-SemiBold.ttf";
    public static String TF_ENG_JOSEFIN_SLAB_SEMI_BOLD_ITALIC = "fonts/ENG_Josefin_Slab/JosefinSlab-SemiBoldItalic.ttf";
    public static String TF_ENG_JOSEFIN_SLAB_THIN = "fonts/ENG_Josefin_Slab/JosefinSlab-Thin.ttf";
    public static String TF_ENG_JOSEFIN_SLAB_THIN_ITALIC = "fonts/ENG_Josefin_Slab/JosefinSlab-ThinItalic.ttf";

    public static String TF_ENG_JURA_DEMI_BOLD = "fonts/ENG_Jura/Jura-DemiBold.ttf";
    public static String TF_ENG_JURA_LIGHT = "fonts/ENG_Jura/Jura-Light.ttf";
    public static String TF_ENG_JURA_MEDIUM = "fonts/ENG_Jura/Jura-Medium.ttf";
    public static String TF_ENG_JURA_REGULAR = "fonts/ENG_Jura/Jura-Regular.ttf";

    public static String TF_ENG_MARVEL_BOLD = "fonts/ENG_Marvel/Marvel-Bold.ttf";
    public static String TF_ENG_MARVEL_BOLD_ITALIC = "fonts/ENG_Marvel/Marvel-BoldItalic.ttf";
    public static String TF_ENG_MARVEL_ITALIC = "fonts/ENG_Marvel/Marvel-Italic.ttf";
    public static String TF_ENG_MARVEL_REGULAR = "fonts/ENG_Marvel/Marvel-Regular.ttf";

    public static String TF_ENG_OPEN_SANS_CONDENSED_COND_BOLD = "fonts/ENG_Open_Sans_Condensed/OpenSans-CondBold.ttf";
    public static String TF_ENG_OPEN_SANS_CONDENSED_COND_LIGHT = "fonts/ENG_Open_Sans_Condensed/OpenSans-CondLight.ttf";
    public static String TF_ENG_OPEN_SANS_CONDENSED_COND_LIGHT_ITALIC = "fonts/ENG_Open_Sans_Condensed/OpenSans-CondLightItalic.ttf";

    public static String TF_ENG_ORBITRON_BLACK = "fonts/ENG_Orbitron/Orbitron-Black.ttf";
    public static String TF_ENG_ORBITRON_BOLD = "fonts/ENG_Orbitron/Orbitron-Bold.ttf";
    public static String TF_ENG_ORBITRON_MEDIUM = "fonts/ENG_Orbitron/Orbitron-Medium.ttf";
    public static String TF_ENG_ORBITRON_REGULAR = "fonts/ENG_Orbitron/Orbitron-Regular.ttf";

    public static String TF_ENG_OSWALD_BOLD = "fonts/ENG_Oswald/Oswald-Bold.ttf";
    public static String TF_ENG_OSWALD_EXTRA_LIGHT = "fonts/ENG_Oswald/Oswald-ExtraLight.ttf";
    public static String TF_ENG_OSWALD_LIGHT = "fonts/ENG_Oswald/Oswald-Light.ttf";
    public static String TF_ENG_OSWALD_MEDIUM = "fonts/ENG_Oswald/Oswald-Medium.ttf";
    public static String TF_ENG_OSWALD_REGULAR = "fonts/ENG_Oswald/Oswald-Regular.ttf";
    public static String TF_ENG_OSWALD_SEMI_BOLD = "fonts/ENG_Oswald/Oswald-SemiBold.ttf";

    public static String TF_ENG_PAJDHANI_BOLD = "fonts/ENG_Rajdhani/Rajdhani-Bold.ttf";
    public static String TF_ENG_PAJDHANI_LIGHT = "fonts/ENG_Rajdhani/Rajdhani-Light.ttf";
    public static String TF_ENG_PAJDHANI_MEDIUM = "fonts/ENG_Rajdhani/Rajdhani-Medium.ttf";
    public static String TF_ENG_PAJDHANI_REGULAR = "fonts/ENG_Rajdhani/Rajdhani-Regular.ttf";
    public static String TF_ENG_PAJDHANI_SEMI_BOLD = "fonts/ENG_Rajdhani/Rajdhani-SemiBold.ttf";

    //--- одиночки
    public static String TF_ENG_ABEL_REGULAR = "fonts/ENG_Abel/Abel-Regular.ttf";
    public static String TF_ENG_COMING_SOON = "fonts/ENG_Coming_Soon/ComingSoon.ttf";
    public static String TF_ENG_ELECTROLIZE_REGULAR = "fonts/ENG_Electrolize/Electrolize-Regular.ttf";
    public static String TF_ENG_EB_GARAMOND_REGULAR = "fonts/ENG_EB_Garamond/EBGaramond-Regular.ttf";
    public static String TF_ENG_INDIE_FLOWER = "fonts/ENG_Indie_Flower/IndieFlower.ttf";
    public static String TF_ENG_NIXIE_ONE_REGULAR = "fonts/ENG_Nixie_One/NixieOne-Regular.ttf";
    public static String TF_ENG_PATHWAY_GOTHIC_ONE_REGULAR = "fonts/ENG_Pathway_Gothic_One/PathwayGothicOne-Regular.ttf";

    //==============================================================================================

    /**
     * Получение списка всех шрифтов
     *
     * @return (1) --
     */
    public static Typeface[] getTypefaces() {
        Typeface[] ret = {};
        Roih receiver = new Roih();
        Field[] fields = Roih.class.getFields();
        for (Field eField : fields) {
            String name = eField.getName();
            if (name.substring(0, 3).equals("TF_")) {
                String fontPath;
                try {
                    fontPath = (String) eField.get(receiver);
                    Typeface tf = Typeface.createFromAsset(Bysa_01.appContext.getAssets(), fontPath);
                    ret = ArrayUtils.add(ret, tf);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

    /**
     * Получение списка всех шрифтов
     *
     * @return (1) --
     */
    public static Xiri[] getXiries() {
        Xiri[] ret = {};
        Roih receiver = new Roih();
        Field[] fields = Roih.class.getFields();
        for (Field eField : fields) {
            String name = eField.getName();
            if (name.substring(0, 3).equals("TF_")) {
                String fontPath;
                try {
                    fontPath = (String) eField.get(receiver);
                    Typeface tf = Typeface.createFromAsset(Bysa_01.appContext.getAssets(), fontPath);
                    Xiri xiri = new Xiri();
                    xiri.maep = name;
                    xiri.tf = tf;
                    ret = ArrayUtils.add(ret, xiri);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

    public static Xiri getXiriByMaep(String maep) {
        Xiri xiri = null;
        try {
            Field field = Roih.class.getField(maep);
            Typeface tf = (Typeface) field.get(new Roih());
            xiri = new Xiri();
            xiri.maep = maep;
            xiri.tf = tf;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return xiri;
    }

    /**
     * Получение шрифта по пути к шрифту
     *
     * @param fontPath (1) -- путь к шрифту
     * @return --
     */
    public static Typeface get(String fontPath) {
        return Typeface.createFromAsset(Bysa_01.appContext.getAssets(), fontPath);
    }

    /**
     * Получение слоя содержащего все шрифты представленные в текущем объекте
     *
     * @return --
     */
    public static NLinearLayout getLayAllTypefaces() {

        NLinearLayout lay = new BuLayLinear_01(Bysa_01.appContext)
                .addChild(new BuilderTV(Bysa_01.appContext)
                        .text("ШТИФТЫ")
                        .textColor(Color.BLACK)
                        .bgColor(Color.YELLOW)
                        .textSize(18)
                        .create())
                .gravitySelf(EGravity.CH.val)
                .margins(0, 16, 0, 0)
                .build();

        //=== название шрифта
        m53(lay);

        return lay;
    }

    private static void m53(NLinearLayout lay) {
        Field[] fields = Roih.class.getFields();
        Roih receiver = new Roih();
        for (Field eField : fields) {
            String name = eField.getName();
            if (name.substring(0, 3).equals("TF_")) {
                //=== виджет имени шрифта
                new BuilderTV(Bysa_01.appContext)
                        .addTo(lay)
                        .text(name)
                        .textColor(Color.LTGRAY)
                        .margins(0, 10, 0, 0)
                        .create();
                //=== виджет с примером текста
                String fontPath = null;
                try {
                    fontPath = (String) eField.get(receiver);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                Typeface tf = Typeface.createFromAsset(Bysa_01.appContext.getAssets(), fontPath);
                new BuilderTV(Bysa_01.appContext)
                        .addTo(lay)
                        .textSize(20)
                        .textColor(Color.BLACK)
                        .textFont(tf)
                        .text("Пример текста, Text example (ш-щ-ц; 76:14:00086:28)")
                        .create();
            }
        }
    }

}
