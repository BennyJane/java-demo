package org.example.com.leetcode.dp.middle;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 1301. 最大得分的路径数目
 * https://leetcode-cn.com/problems/number-of-paths-with-max-score/
 */
public class Q11 {

    Pair<Integer, Integer>[][] dp;
    int mod = (int) 1e9 + 7;

    public int[] pathsWithMaxScore(List<String> board) {
        int m = board.size();
        // dp[x][y]: 表示坐标(x, y)位置处，取得最大得分以及数量
        dp = new Pair[m][m];
        dp[m - 1][m - 1] = new Pair<>(0, 1);
        dp[0][0] = new Pair<>(0, 1);
        //  初始化状态
        for (int i = m - 2; i >= 0; i--) {
            // 最后一行
            if (board.get(m - 1).charAt(i) == 'X' || dp[m - 1][i + 1].getKey() == -1) {
                dp[m - 1][i] = new Pair<>(-1, 1);
            } else {
                int next = Integer.parseInt(String.valueOf(board.get(m - 1).charAt(i)));
                dp[m - 1][i] = new Pair<>(dp[m - 1][i + 1].getKey() + next, 1);
            }

            // 最后一列
            if (board.get(i).charAt(m - 1) == 'X' || dp[i + 1][m - 1].getKey() == -1) {
                dp[i][m - 1] = new Pair<>(-1, 1);
            } else {
                int next = Integer.parseInt(String.valueOf(board.get(i).charAt(m - 1)));
                dp[i][m - 1] = new Pair<>(dp[i + 1][m - 1].getKey() + next, 1);
            }
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                // 先判断当前位置类型
                if (board.get(i).charAt(j) == 'X') {
                    dp[i][j] = new Pair<>(-1, 1);
                    continue;
                }
                if (board.get(i).charAt(j) == 'E') {
                    int[] res = check(0, 0);
                    if (res[0] == -1) {
                        return new int[2];
                    }
                    return res;
                }

                // 数值类型
                int curNum = Integer.parseInt(String.valueOf(board.get(i).charAt(j)));
                int[] res = check(i, j);
                if (res[0] == -1) { // 三个来源都是X时，路径不通
                    dp[i][j] = new Pair<>(-1, 1);
                } else {
                    dp[i][j] = new Pair<>(curNum + res[0], res[1]);
                }
            }
        }

        return new int[2];
    }

    private int[] check(int x, int y) {
        int goal = 0;
        int count = 0;
        if (dp[x][y + 1].getKey() > dp[x + 1][y].getKey()) {
            goal = dp[x][y + 1].getKey();
            count = dp[x][y + 1].getValue();
        } else if (dp[x][y + 1].getKey().equals(dp[x + 1][y].getKey())) {
            goal = dp[x + 1][y].getKey();
            count = dp[x + 1][y].getValue() + dp[x][y + 1].getValue();
        } else {
            goal = dp[x + 1][y].getKey();
            count = dp[x + 1][y].getValue();
        }

        // 如果没有使用if else if 连续；一般要先比较等于的情况，再考虑不等的情况，
        if (dp[x + 1][y + 1].getKey().equals(goal)) {
            count += dp[x + 1][y + 1].getValue();
        }else if (dp[x + 1][y + 1].getKey() > goal) {
            goal = dp[x + 1][y + 1].getKey();
            count = dp[x + 1][y + 1].getValue();
        } else {

        }

        return new int[]{goal % mod, count % mod};
    }

    public static void main(String[] args) {
        Q11 q = new Q11();
        List<String> list = new ArrayList<>();
//        list.add("E23");
//        list.add("2X2");
//        list.add("12S");

//        list.add("E11");
//        list.add("XXX");
//        list.add("11S");

        list.add("EX");
        list.add("XS");

//        q.pathsWithMaxScore(list);
        String[] strList = new String[]{
                "E4789338X943596124X2676X552X587877X456943458X29735", "611684759486631913932337237231351921X2152919376427", "4499519117827344997451XX34X46693XX7181343557483669", "414951X685152X89829782685X4912581351X3216914721551", "X387271851925X3629265X99195X5897581179XX369637813X", "1X8X2682518937289551X98X7983XX34993116413343558825", "X92X12119593186X675113X682143777XX8981619298251984", "X671798198463X5314971262X9392393XXX544537813812728", "81856146535454X3678775784456289257XX8221X2488XXX68", "77X3592XX94844399282X2X6336122XX7X18244862821X26XX", "28885X948512X3585X27824186222X73X9X56441X9X4689517", "344X495X682875968X82X9877379XX386748175X6293X44159", "1924352186149295919715X27X555626X17798524189528625", "3435681879X49727366745492X648X5952772978787143263X", "412933788234154913356X2X9X144818X21XX5629259785133", "489644765X456XX44XX2X5387637879X662941398337817381", "7617826679176XX173173537173164967296764519X3427693", "7X69X5466277665871135253486758156766536X5436X16728", "318152574426X696X18X833396113X31862234511611X89691", "X147492241256344555237X94772X9X5136226469551942X29", "X29846X49419853778154XX636X35XX5232391787617416258", "8885851X996538X163323347235993741926591X72X1761X14", "226X8136988863232963682217521777419144333838517835", "3197757518X241117949451X423XX55861XX6161938551X752", "49X149355329X617X51X21965452X962X42762X25968X73754", "X1176483834957794897816132X5X942366794665183797399", "48294674492176X1X663644X58X17729X4482638X92X482422", "51X16X157889846864X1436338776687677X44895154219626", "782XX82XX432848434721692X55564975938X2649681569663", "26792517214X8863X9896XX64619817916123168945761X526", "X7XX319393797X184679X421943743279X1486126364341595", "11425763X68563511X2496983325426X151456X5X459542989", "89872654932254X5525587692326476X65562X59X635129314", "6X944371656471737X9673645X4145X821X942995885X86522", "64XX328X8243XX3445X6412955X87X42355234X73223243421", "944391299X158X962X9X259552884441434XX9349X5XX79855", "98422711429356771336494176X56X2584376X2354X72X5416", "7135X7X9X3X699929714X61664916968X1896X5X1985386642", "22969X77414X2154167176388X3313X918X1558161XX413862", "4X141958614412616921588565488847635X837996835937X6", "X416X64649X955369739187781488XX77129X6966899351X74", "9X2349175X345X7469265842X591X4748167996963X63X7211", "3117349374592412365636726147X52469X8921X1888159627", "254513X73629359514989286822X7X89391797X357546X9145", "8882877128738295914941X56995X243888XX595873XX99217", "X947591X382X19613453263544415821689719794546655278", "51X4565271954965486X492693X731844471486X149X7166X5", "731X1816268181645946X2123376981X13X834338226X28X36", "6X72X3599546159X378191718821X746789239488712584143", "5982153336X61729X66339596838X77751396645929982913S"
        };
        List<String> res = Arrays.stream(strList).collect(Collectors.toList());
        q.pathsWithMaxScore(res);
    }
}


