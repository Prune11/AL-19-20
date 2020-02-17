import 'package:charts_flutter/flutter.dart' as charts;
import 'package:flutter/material.dart';

class VerticalGroupBarLabelChart extends StatelessWidget {
  static const String FEES = "Fees";
  static const String FEES_WOOD = "Fees wood";
  static const String FEES_STONE = "Fees stone";
  static const String FEES_IRON = "Fees iron";
  static const String FEES_DIAMOND = "Fees diamond";
  static const String AVG = "Avg";
  static const String AVG_WOOD = "Avg wood";
  static const String AVG_STONE = "Avg stone";
  static const String AVG_IRON = "Avg iron";
  static const String AVG_DIAMOND = "Avg diamond";

  final List<charts.Series> seriesList;
  final bool animate;

  VerticalGroupBarLabelChart(this.seriesList, {this.animate});

  factory VerticalGroupBarLabelChart.feesPerDay(/*Map<DateTime, double> data*/) {
    return new VerticalGroupBarLabelChart(
      _createFeesPerDay(/*data*/),
      animate: true,
    );
  }

  factory VerticalGroupBarLabelChart.avgPerDay(/*Map<DateTime, double> data*/) {
    return new VerticalGroupBarLabelChart(
      _createAvgPerDay(/*data*/),
      animate: true,
    );
  }


  // [BarLabelDecorator] will automatically position the label
  // inside the bar if the label will fit. If the label will not fit,
  // it will draw outside of the bar.
  // Labels can always display inside or outside using [LabelPosition].
  //
  // Text style for inside / outside can be controlled independently by setting
  // [insideLabelStyleSpec] and [outsideLabelStyleSpec].
  @override
  Widget build(BuildContext context) {
    return new charts.BarChart(
      seriesList,
      animate: animate,
      //barRendererDecorator: new charts.BarLabelDecorator<String>(),
      //domainAxis: new charts.OrdinalAxisSpec(),
      barGroupingType: charts.BarGroupingType.grouped,
      behaviors: [
        new charts.ChartTitle(getTitle(seriesList),
            behaviorPosition: charts.BehaviorPosition.top,
            titleOutsideJustification: charts.OutsideJustification.start,
            innerPadding: 20
        ),
      ],
    );
  }

  /// Create one series with sample hard coded data.
  static List<charts.Series<CumulativeFees, String>> _createFeesPerDay(/*Map<DateTime, double> data*/) {
    final dataWood = [
      new CumulativeFees('Janvier', 5.0),
      new CumulativeFees('Fevrier', 25.3),
      new CumulativeFees('Mars', 100.8),
      new CumulativeFees('Avril', 75.7),
    ];

    final dataStone = [
      new CumulativeFees('Janvier', 7.0),
      new CumulativeFees('Fevrier', 21.3),
      new CumulativeFees('Mars', 10.8),
      new CumulativeFees('Avril', 83.7),
    ];

    final dataIron = [
      new CumulativeFees('Janvier', 100.0),
      new CumulativeFees('Fevrier', 2.3),
      new CumulativeFees('Mars', 18.5),
      new CumulativeFees('Avril', 27.8),
    ];

    final dataDiamond = [
      new CumulativeFees('Janvier', 8.3),
      new CumulativeFees('Fevrier', 23.5),
      new CumulativeFees('Mars', 10.9),
      new CumulativeFees('Avril', 57.9),
    ];

    return [
      new charts.Series<CumulativeFees, String>(
        id: FEES_WOOD,
        domainFn: (CumulativeFees cF, _) => cF.month,
        measureFn: (CumulativeFees cF, _) => cF.fees,
        data: dataWood,
        // Set a label accessor to control the text of the bar label.
        labelAccessorFn: (CumulativeFees cF, _) =>
        '${cF.fees.toString()} €',
        //Change fill color
        colorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
        fillColorFn: (_, __) =>
        charts.MaterialPalette.teal.shadeDefault,
      ),
      new charts.Series<CumulativeFees, String>(
        id: FEES_STONE,
        domainFn: (CumulativeFees cF, _) => cF.month,
        measureFn: (CumulativeFees cF, _) => cF.fees,
        data: dataStone,
        // Set a label accessor to control the text of the bar label.
        labelAccessorFn: (CumulativeFees cF, _) =>
        '${cF.fees.toString()} €',
        //Change fill color
        colorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
        fillColorFn: (_, __) =>
        charts.MaterialPalette.teal.shadeDefault.lighter.lighter,
      ),
      new charts.Series<CumulativeFees, String>(
        id: FEES_IRON,
        domainFn: (CumulativeFees cF, _) => cF.month,
        measureFn: (CumulativeFees cF, _) => cF.fees,
        data: dataIron,
        // Set a label accessor to control the text of the bar label.
        labelAccessorFn: (CumulativeFees cF, _) =>
        '${cF.fees.toString()} €',
        //Change fill color
        colorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
        fillColorFn: (_, __) =>
        charts.MaterialPalette.teal.shadeDefault.darker,
      ),
      new charts.Series<CumulativeFees, String>(
        id: FEES_DIAMOND,
        domainFn: (CumulativeFees cF, _) => cF.month,
        measureFn: (CumulativeFees cF, _) => cF.fees,
        data: dataDiamond,
        // Set a label accessor to control the text of the bar label.
        labelAccessorFn: (CumulativeFees cF, _) =>
        '${cF.fees.toString()} €',
        //Change fill color
        colorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
        fillColorFn: (_, __) =>
        charts.MaterialPalette.teal.shadeDefault.lighter,
      )
    ];
  }

  static List<charts.Series<CumulativeFees, String>> _createAvgPerDay(/*Map<DateTime, double> data*/) {
    final dataWood = [
      new CumulativeFees('13-02-2020', 5.0),
      new CumulativeFees('14-02-2020', 25.3),
      new CumulativeFees('15-02-2020', 22.2),
      new CumulativeFees('16-02-2020', 37.5),
    ];

    final dataStone = [
      new CumulativeFees('13-02-2020', 5.8),
      new CumulativeFees('14-02-2020', 26.0),
      new CumulativeFees('15-02-2020', 18.2),
      new CumulativeFees('16-02-2020', 26.3),
    ];

    final dataIron = [
      new CumulativeFees('13-02-2020', 4.6),
      new CumulativeFees('14-02-2020', 25.9),
      new CumulativeFees('15-02-2020', 23.2),
      new CumulativeFees('16-02-2020', 32.5),
    ];

    final dataDiamond = [
      new CumulativeFees('13-02-2020', 5.1),
      new CumulativeFees('14-02-2020', 26.3),
      new CumulativeFees('15-02-2020', 22.4),
      new CumulativeFees('16-02-2020', 36.5),
    ];

    return [
      new charts.Series<CumulativeFees, String>(
        id: AVG_WOOD,
        domainFn: (CumulativeFees cF, _) => cF.month,
        measureFn: (CumulativeFees cF, _) => cF.fees,
        data: dataWood,
        // Set a label accessor to control the text of the bar label.
        labelAccessorFn: (CumulativeFees cF, _) =>
        '${cF.fees.toString()} €',
        //Change fill color
        colorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
        fillColorFn: (_, __) =>
        charts.MaterialPalette.teal.shadeDefault,
      ),
      new charts.Series<CumulativeFees, String>(
        id: AVG_STONE,
        domainFn: (CumulativeFees cF, _) => cF.month,
        measureFn: (CumulativeFees cF, _) => cF.fees,
        data: dataStone,
        // Set a label accessor to control the text of the bar label.
        labelAccessorFn: (CumulativeFees cF, _) =>
        '${cF.fees.toString()} €',
        //Change fill color
        colorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
        fillColorFn: (_, __) =>
        charts.MaterialPalette.teal.shadeDefault.lighter.lighter,
      ),
      new charts.Series<CumulativeFees, String>(
        id: AVG_IRON,
        domainFn: (CumulativeFees cF, _) => cF.month,
        measureFn: (CumulativeFees cF, _) => cF.fees,
        data: dataIron,
        // Set a label accessor to control the text of the bar label.
        labelAccessorFn: (CumulativeFees cF, _) =>
        '${cF.fees.toString()} €',
        //Change fill color
        colorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
        fillColorFn: (_, __) =>
        charts.MaterialPalette.teal.shadeDefault.darker,
      ),
      new charts.Series<CumulativeFees, String>(
        id: AVG_DIAMOND,
        domainFn: (CumulativeFees cF, _) => cF.month,
        measureFn: (CumulativeFees cF, _) => cF.fees,
        data: dataDiamond,
        // Set a label accessor to control the text of the bar label.
        labelAccessorFn: (CumulativeFees cF, _) =>
        '${cF.fees.toString()} €',
        //Change fill color
        colorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
        fillColorFn: (_, __) =>
        charts.MaterialPalette.teal.shadeDefault.lighter,
      ),

    ];
  }

  static String getTitle(List<charts.Series<CumulativeFees, String>> chartList){
    String title = "";
    String chartID = chartList[0].id;
    if (chartID.contains(FEES)) {
      return "Comparative FEES per day";
    }
    else if ( chartID.contains(AVG)) {
      return "Comparative AVERAGE per day";
    }
    return title;
  }
}

/// Sample ordinal data type.
class CumulativeFees {
  final String month;
  final double fees;

  CumulativeFees(this.month, this.fees);
}