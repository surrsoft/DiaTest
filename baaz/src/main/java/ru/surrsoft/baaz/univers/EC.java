package ru.surrsoft.baaz.univers;


import ru.surrsoft.baaz.Bysa_01;
import ru.surrsoft.baaz.R;

/**
 * //
 */
public enum EC {

    MD_TRANSPARENT(0),

    //reds
    MD_RED_50(get(R.color.md_red_50)),
    MD_RED_100(get(R.color.md_red_100)),
    MD_RED_200(get(R.color.md_red_200)),
    MD_RED_300(get(R.color.md_red_300)),
    MD_RED_400(get(R.color.md_red_400)),
    MD_RED_500(get(R.color.md_red_500)),
    MD_RED_600(get(R.color.md_red_600)),
    MD_RED_700(get(R.color.md_red_700)),
    MD_RED_800(get(R.color.md_red_800)),
    MD_RED_900(get(R.color.md_red_900)),
    MD_RED_A100(get(R.color.md_red_A100)),
    MD_RED_A200(get(R.color.md_red_A200)),
    MD_RED_A400(get(R.color.md_red_A400)),
    MD_RED_A700(get(R.color.md_red_A700)),

    // pinks
    MD_PINK_50(get(R.color.md_pink_50)),
    MD_PINK_100(get(R.color.md_pink_100)),
    MD_PINK_200(get(R.color.md_pink_200)),
    MD_PINK_300(get(R.color.md_pink_300)),
    MD_PINK_400(get(R.color.md_pink_400)),
    MD_PINK_500(get(R.color.md_pink_500)),
    MD_PINK_600(get(R.color.md_pink_600)),
    MD_PINK_700(get(R.color.md_pink_700)),
    MD_PINK_800(get(R.color.md_pink_800)),
    MD_PINK_900(get(R.color.md_pink_900)),
    MD_PINK_A100(get(R.color.md_pink_A100)),
    MD_PINK_A200(get(R.color.md_pink_A200)),
    MD_PINK_A400(get(R.color.md_pink_A400)),
    MD_PINK_A700(get(R.color.md_pink_A700)),

    // purples
    MD_PURPLE_50(get(R.color.md_purple_50)),
    MD_PURPLE_100(get(R.color.md_purple_100)),
    MD_PURPLE_200(get(R.color.md_purple_200)),
    MD_PURPLE_300(get(R.color.md_purple_300)),
    MD_PURPLE_400(get(R.color.md_purple_400)),
    MD_PURPLE_500(get(R.color.md_purple_500)),
    MD_PURPLE_600(get(R.color.md_purple_600)),
    MD_PURPLE_700(get(R.color.md_purple_700)),
    MD_PURPLE_800(get(R.color.md_purple_800)),
    MD_PURPLE_900(get(R.color.md_purple_900)),
    MD_PURPLE_A100(get(R.color.md_purple_A100)),
    MD_PURPLE_A200(get(R.color.md_purple_A200)),
    MD_PURPLE_A400(get(R.color.md_purple_A400)),
    MD_PURPLE_A700(get(R.color.md_purple_A700)),

    // deep purples
    MD_DEEP_PURPLE_50(get(R.color.md_deep_purple_50)),
    MD_DEEP_PURPLE_100(get(R.color.md_deep_purple_100)),
    MD_DEEP_PURPLE_200(get(R.color.md_deep_purple_200)),
    MD_DEEP_PURPLE_300(get(R.color.md_deep_purple_300)),
    MD_DEEP_PURPLE_400(get(R.color.md_deep_purple_400)),
    MD_DEEP_PURPLE_500(get(R.color.md_deep_purple_500)),
    MD_DEEP_PURPLE_600(get(R.color.md_deep_purple_600)),
    MD_DEEP_PURPLE_700(get(R.color.md_deep_purple_700)),
    MD_DEEP_PURPLE_800(get(R.color.md_deep_purple_800)),
    MD_DEEP_PURPLE_900(get(R.color.md_deep_purple_900)),
    MD_DEEP_PURPLE_A100(get(R.color.md_deep_purple_A100)),
    MD_DEEP_PURPLE_A200(get(R.color.md_deep_purple_A200)),
    MD_DEEP_PURPLE_A400(get(R.color.md_deep_purple_A400)),
    MD_DEEP_PURPLE_A700(get(R.color.md_deep_purple_A700)),

    // indigo
    MD_INDIGO_50(get(R.color.md_indigo_50)),
    MD_INDIGO_100(get(R.color.md_indigo_100)),
    MD_INDIGO_200(get(R.color.md_indigo_200)),
    MD_INDIGO_300(get(R.color.md_indigo_300)),
    MD_INDIGO_400(get(R.color.md_indigo_400)),
    MD_INDIGO_500(get(R.color.md_indigo_500)),
    MD_INDIGO_600(get(R.color.md_indigo_600)),
    MD_INDIGO_700(get(R.color.md_indigo_700)),
    MD_INDIGO_800(get(R.color.md_indigo_800)),
    MD_INDIGO_900(get(R.color.md_indigo_900)),
    MD_INDIGO_A100(get(R.color.md_indigo_A100)),
    MD_INDIGO_A200(get(R.color.md_indigo_A200)),
    MD_INDIGO_A400(get(R.color.md_indigo_A400)),
    MD_INDIGO_A700(get(R.color.md_indigo_A700)),

    //blue
    MD_BLUE_50(get(R.color.md_blue_50)),
    MD_BLUE_100(get(R.color.md_blue_100)),
    MD_BLUE_200(get(R.color.md_blue_200)),
    MD_BLUE_300(get(R.color.md_blue_300)),
    MD_BLUE_400(get(R.color.md_blue_400)),
    MD_BLUE_500(get(R.color.md_blue_500)),
    MD_BLUE_600(get(R.color.md_blue_600)),
    MD_BLUE_700(get(R.color.md_blue_700)),
    MD_BLUE_800(get(R.color.md_blue_800)),
    MD_BLUE_900(get(R.color.md_blue_900)),
    MD_BLUE_A100(get(R.color.md_blue_A100)),
    MD_BLUE_A200(get(R.color.md_blue_A200)),
    MD_BLUE_A400(get(R.color.md_blue_A400)),
    MD_BLUE_A700(get(R.color.md_blue_A700)),

    // light blue
    MD_LIGHT_BLUE_50(get(R.color.md_light_blue_50)),
    MD_LIGHT_BLUE_100(get(R.color.md_light_blue_100)),
    MD_LIGHT_BLUE_200(get(R.color.md_light_blue_200)),
    MD_LIGHT_BLUE_300(get(R.color.md_light_blue_300)),
    MD_LIGHT_BLUE_400(get(R.color.md_light_blue_400)),
    MD_LIGHT_BLUE_500(get(R.color.md_light_blue_500)),
    MD_LIGHT_BLUE_600(get(R.color.md_light_blue_600)),
    MD_LIGHT_BLUE_700(get(R.color.md_light_blue_700)),
    MD_LIGHT_BLUE_800(get(R.color.md_light_blue_800)),
    MD_LIGHT_BLUE_900(get(R.color.md_light_blue_900)),
    MD_LIGHT_BLUE_A100(get(R.color.md_light_blue_A100)),
    MD_LIGHT_BLUE_A200(get(R.color.md_light_blue_A200)),
    MD_LIGHT_BLUE_A400(get(R.color.md_light_blue_A400)),
    MD_LIGHT_BLUE_A700(get(R.color.md_light_blue_A700)),

    // cyan
    MD_CYAN_50(get(R.color.md_cyan_50)),
    MD_CYAN_100(get(R.color.md_cyan_100)),
    MD_CYAN_200(get(R.color.md_cyan_200)),
    MD_CYAN_300(get(R.color.md_cyan_300)),
    MD_CYAN_400(get(R.color.md_cyan_400)),
    MD_CYAN_500(get(R.color.md_cyan_500)),
    MD_CYAN_600(get(R.color.md_cyan_600)),
    MD_CYAN_700(get(R.color.md_cyan_700)),
    MD_CYAN_800(get(R.color.md_cyan_800)),
    MD_CYAN_900(get(R.color.md_cyan_900)),
    MD_CYAN_A100(get(R.color.md_cyan_A100)),
    MD_CYAN_A200(get(R.color.md_cyan_A200)),
    MD_CYAN_A400(get(R.color.md_cyan_A400)),
    MD_CYAN_A700(get(R.color.md_cyan_A700)),

    // teal
    MD_TEAL_50(get(R.color.md_teal_50)),
    MD_TEAL_100(get(R.color.md_teal_100)),
    MD_TEAL_200(get(R.color.md_teal_200)),
    MD_TEAL_300(get(R.color.md_teal_300)),
    MD_TEAL_400(get(R.color.md_teal_400)),
    MD_TEAL_500(get(R.color.md_teal_500)),
    MD_TEAL_600(get(R.color.md_teal_600)),
    MD_TEAL_700(get(R.color.md_teal_700)),
    MD_TEAL_800(get(R.color.md_teal_800)),
    MD_TEAL_900(get(R.color.md_teal_900)),
    MD_TEAL_A100(get(R.color.md_teal_A100)),
    MD_TEAL_A200(get(R.color.md_teal_A200)),
    MD_TEAL_A400(get(R.color.md_teal_A400)),
    MD_TEAL_A700(get(R.color.md_teal_A700)),

    // green
    MD_GREEN_50(get(R.color.md_green_50)),
    MD_GREEN_100(get(R.color.md_green_100)),
    MD_GREEN_200(get(R.color.md_green_200)),
    MD_GREEN_300(get(R.color.md_green_300)),
    MD_GREEN_400(get(R.color.md_green_400)),
    MD_GREEN_500(get(R.color.md_green_500)),
    MD_GREEN_600(get(R.color.md_green_600)),
    MD_GREEN_700(get(R.color.md_green_700)),
    MD_GREEN_800(get(R.color.md_green_800)),
    MD_GREEN_900(get(R.color.md_green_900)),
    MD_GREEN_A100(get(R.color.md_green_A100)),
    MD_GREEN_A200(get(R.color.md_green_A200)),
    MD_GREEN_A400(get(R.color.md_green_A400)),
    MD_GREEN_A700(get(R.color.md_green_A700)),

    //light green
    MD_LIGHT_GREEN_50(get(R.color.md_light_green_50)),
    MD_LIGHT_GREEN_100(get(R.color.md_light_green_100)),
    MD_LIGHT_GREEN_200(get(R.color.md_light_green_200)),
    MD_LIGHT_GREEN_300(get(R.color.md_light_green_300)),
    MD_LIGHT_GREEN_400(get(R.color.md_light_green_400)),
    MD_LIGHT_GREEN_500(get(R.color.md_light_green_500)),
    MD_LIGHT_GREEN_600(get(R.color.md_light_green_600)),
    MD_LIGHT_GREEN_700(get(R.color.md_light_green_700)),
    MD_LIGHT_GREEN_800(get(R.color.md_light_green_800)),
    MD_LIGHT_GREEN_900(get(R.color.md_light_green_900)),
    MD_LIGHT_GREEN_A100(get(R.color.md_light_green_A100)),
    MD_LIGHT_GREEN_A200(get(R.color.md_light_green_A200)),
    MD_LIGHT_GREEN_A400(get(R.color.md_light_green_A400)),
    MD_LIGHT_GREEN_A700(get(R.color.md_light_green_A700)),

    // lime
    MD_LIME_50(get(R.color.md_lime_50)),
    MD_LIME_100(get(R.color.md_lime_100)),
    MD_LIME_200(get(R.color.md_lime_200)),
    MD_LIME_300(get(R.color.md_lime_300)),
    MD_LIME_400(get(R.color.md_lime_400)),
    MD_LIME_500(get(R.color.md_lime_500)),
    MD_LIME_600(get(R.color.md_lime_600)),
    MD_LIME_700(get(R.color.md_lime_700)),
    MD_LIME_800(get(R.color.md_lime_800)),
    MD_LIME_900(get(R.color.md_lime_900)),
    MD_LIME_A100(get(R.color.md_lime_A100)),
    MD_LIME_A200(get(R.color.md_lime_A200)),
    MD_LIME_A400(get(R.color.md_lime_A400)),
    MD_LIME_A700(get(R.color.md_lime_A700)),

    //yellow
    MD_YELLOW_50(get(R.color.md_yellow_50)),
    MD_YELLOW_100(get(R.color.md_yellow_100)),
    MD_YELLOW_200(get(R.color.md_yellow_200)),
    MD_YELLOW_300(get(R.color.md_yellow_300)),
    MD_YELLOW_400(get(R.color.md_yellow_400)),
    MD_YELLOW_500(get(R.color.md_yellow_500)),
    MD_YELLOW_600(get(R.color.md_yellow_600)),
    MD_YELLOW_700(get(R.color.md_yellow_700)),
    MD_YELLOW_800(get(R.color.md_yellow_800)),
    MD_YELLOW_900(get(R.color.md_yellow_900)),
    MD_YELLOW_A100(get(R.color.md_yellow_A100)),
    MD_YELLOW_A200(get(R.color.md_yellow_A200)),
    MD_YELLOW_A400(get(R.color.md_yellow_A400)),
    MD_YELLOW_A700(get(R.color.md_yellow_A700)),

    //amber
    MD_AMBER_50(get(R.color.md_amber_50)),
    MD_AMBER_100(get(R.color.md_amber_100)),
    MD_AMBER_200(get(R.color.md_amber_200)),
    MD_AMBER_300(get(R.color.md_amber_300)),
    MD_AMBER_400(get(R.color.md_amber_400)),
    MD_AMBER_500(get(R.color.md_amber_500)),
    MD_AMBER_600(get(R.color.md_amber_600)),
    MD_AMBER_700(get(R.color.md_amber_700)),
    MD_AMBER_800(get(R.color.md_amber_800)),
    MD_AMBER_900(get(R.color.md_amber_900)),
    MD_AMBER_A100(get(R.color.md_amber_A100)),
    MD_AMBER_A200(get(R.color.md_amber_A200)),
    MD_AMBER_A400(get(R.color.md_amber_A400)),
    MD_AMBER_A700(get(R.color.md_amber_A700)),

    //orange
    MD_ORANGE_50(get(R.color.md_orange_50)),
    MD_ORANGE_100(get(R.color.md_orange_100)),
    MD_ORANGE_200(get(R.color.md_orange_200)),
    MD_ORANGE_300(get(R.color.md_orange_300)),
    MD_ORANGE_400(get(R.color.md_orange_400)),
    MD_ORANGE_500(get(R.color.md_orange_500)),
    MD_ORANGE_600(get(R.color.md_orange_600)),
    MD_ORANGE_700(get(R.color.md_orange_700)),
    MD_ORANGE_800(get(R.color.md_orange_800)),
    MD_ORANGE_900(get(R.color.md_orange_900)),
    MD_ORANGE_A100(get(R.color.md_orange_A100)),
    MD_ORANGE_A200(get(R.color.md_orange_A200)),
    MD_ORANGE_A400(get(R.color.md_orange_A400)),
    MD_ORANGE_A700(get(R.color.md_orange_A700)),

    //deep orange
    MD_DEEP_ORANGE_50(get(R.color.md_deep_orange_50)),
    MD_DEEP_ORANGE_100(get(R.color.md_deep_orange_100)),
    MD_DEEP_ORANGE_200(get(R.color.md_deep_orange_200)),
    MD_DEEP_ORANGE_300(get(R.color.md_deep_orange_300)),
    MD_DEEP_ORANGE_400(get(R.color.md_deep_orange_400)),
    MD_DEEP_ORANGE_500(get(R.color.md_deep_orange_500)),
    MD_DEEP_ORANGE_600(get(R.color.md_deep_orange_600)),
    MD_DEEP_ORANGE_700(get(R.color.md_deep_orange_700)),
    MD_DEEP_ORANGE_800(get(R.color.md_deep_orange_800)),
    MD_DEEP_ORANGE_900(get(R.color.md_deep_orange_900)),
    MD_DEEP_ORANGE_A100(get(R.color.md_deep_orange_A100)),
    MD_DEEP_ORANGE_A200(get(R.color.md_deep_orange_A200)),
    MD_DEEP_ORANGE_A400(get(R.color.md_deep_orange_A400)),
    MD_DEEP_ORANGE_A700(get(R.color.md_deep_orange_A700)),

    //brown
    MD_BROWN_50(get(R.color.md_brown_50)),
    MD_BROWN_100(get(R.color.md_brown_100)),
    MD_BROWN_200(get(R.color.md_brown_200)),
    MD_BROWN_300(get(R.color.md_brown_300)),
    MD_BROWN_400(get(R.color.md_brown_400)),
    MD_BROWN_500(get(R.color.md_brown_500)),
    MD_BROWN_600(get(R.color.md_brown_600)),
    MD_BROWN_700(get(R.color.md_brown_700)),
    MD_BROWN_800(get(R.color.md_brown_800)),
    MD_BROWN_900(get(R.color.md_brown_900)),

    //grey
    MD_GREY_50(get(R.color.md_grey_50)),
    MD_GREY_100(get(R.color.md_grey_100)),
    MD_GREY_200(get(R.color.md_grey_200)),
    MD_GREY_300(get(R.color.md_grey_300)),
    MD_GREY_400(get(R.color.md_grey_400)),
    MD_GREY_500(get(R.color.md_grey_500)),
    MD_GREY_600(get(R.color.md_grey_600)),
    MD_GREY_700(get(R.color.md_grey_700)),
    MD_GREY_800(get(R.color.md_grey_800)),
    MD_GREY_900(get(R.color.md_grey_900)),
    MD_BLACK_1000(get(R.color.md_black_1000)),
    MD_WHITE_1000(get(R.color.md_white_1000)),

    //blue grey
    MD_BLUE_GREY_50(get(R.color.md_blue_grey_50)),
    MD_BLUE_GREY_100(get(R.color.md_blue_grey_100)),
    MD_BLUE_GREY_200(get(R.color.md_blue_grey_200)),
    MD_BLUE_GREY_300(get(R.color.md_blue_grey_300)),
    MD_BLUE_GREY_400(get(R.color.md_blue_grey_400)),
    MD_BLUE_GREY_500(get(R.color.md_blue_grey_500)),
    MD_BLUE_GREY_600(get(R.color.md_blue_grey_600)),
    MD_BLUE_GREY_700(get(R.color.md_blue_grey_700)),
    MD_BLUE_GREY_800(get(R.color.md_blue_grey_800)),
    MD_BLUE_GREY_900(get(R.color.md_blue_grey_900));

    public final int val;

    //==============================================================================================
    EC(int color) {
        val = color;
    }

    //``````````````````````````````````````````````````````````````````````````````````````````````
    private static int get(int r) {
        return Bysa_01.appContext.getResources().getColor(r);
    }
}
