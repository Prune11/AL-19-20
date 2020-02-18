import 'package:charts_flutter/flutter.dart' as charts;
import 'package:credirama/model/simulationObject.dart';
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

  factory VerticalGroupBarLabelChart.feesPerDay(Map<String, SimulationObject> data) {
    return new VerticalGroupBarLabelChart(
      _createFeesPerDay(data),
      animate: true,
    );
  }

  factory VerticalGroupBarLabelChart.avgPerDay(Map<String, SimulationObject> data) {
    return new VerticalGroupBarLabelChart(
      _createAvgPerDay(data),
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
            //innerPadding: 20
        ),
        new charts.SeriesLegend(
          position: charts.BehaviorPosition.bottom,
          desiredMaxColumns: 2,
        ),
      ],
    );
  }

  /// Create one series with sample hard coded data.
  static List<charts.Series<CumulativeData, String>> _createFeesPerDay(Map<String, SimulationObject> data) {
    List<CumulativeData> dataWood = [];
    List<CumulativeData> dataStone = [];
    List<CumulativeData> dataIron = [];
    List<CumulativeData> dataDiamond = [];
    for( String date in data["WOOD"].dailyResult.keys){
      dataWood..add(CumulativeData(date, data["WOOD"].dailyResult[date].sum));
      dataStone..add(CumulativeData(date, data["STONE"].dailyResult[date].sum));
      dataIron..add(CumulativeData(date, data["IRON"].dailyResult[date].sum));
      dataDiamond..add(CumulativeData(date, data["DIAMOND"].dailyResult[date].sum));
    }


    return [
      new charts.Series<CumulativeData, String>(
        id: FEES_WOOD,
        domainFn: (CumulativeData cD, _) => cD.date,
        measureFn: (CumulativeData cD, _) => cD.data,
        data: dataWood,
        // Set a label accessor to control the text of the bar label.
        labelAccessorFn: (CumulativeData cD, _) => '${cD.data.toString()} €',
        //Change fill color
        colorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
        fillColorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
      ),
      new charts.Series<CumulativeData, String>(
        id: FEES_STONE,
        domainFn: (CumulativeData cD, _) => cD.date,
        measureFn: (CumulativeData cD, _) => cD.data,
        data: dataStone,
        // Set a label accessor to control the text of the bar label.
        labelAccessorFn: (CumulativeData cD, _) =>'${cD.data.toString()} €',
        //Change fill color
        colorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
        fillColorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault.lighter.lighter,
      ),
      new charts.Series<CumulativeData, String>(
        id: FEES_IRON,
        domainFn: (CumulativeData cD, _) => cD.date,
        measureFn: (CumulativeData cD, _) => cD.data,
        data: dataIron,
        // Set a label accessor to control the text of the bar label.
        labelAccessorFn: (CumulativeData cD, _) => '${cD.data.toString()} €',
        //Change fill color
        colorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
        fillColorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault.darker,
      ),
      new charts.Series<CumulativeData, String>(
        id: FEES_DIAMOND,
        domainFn: (CumulativeData cD, _) => cD.date,
        measureFn: (CumulativeData cD, _) => cD.data,
        data: dataDiamond,
        // Set a label accessor to control the text of the bar label.
        labelAccessorFn: (CumulativeData cD, _) => '${cD.data.toString()} €',
        //Change fill color
        colorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
        fillColorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault.lighter,
      )
    ];
  }

  static List<charts.Series<CumulativeData, String>> _createAvgPerDay(Map<String, SimulationObject> data) {
    List<CumulativeData> dataWood = [];
    List<CumulativeData> dataStone = [];
    List<CumulativeData> dataIron = [];
    List<CumulativeData> dataDiamond = [];
    for( String date in data["WOOD"].dailyResult.keys){
      dataWood..add(CumulativeData(date, data["WOOD"].dailyResult[date].avg));
      dataStone..add(CumulativeData(date, data["STONE"].dailyResult[date].avg));
      dataIron..add(CumulativeData(date, data["IRON"].dailyResult[date].avg));
      dataDiamond..add(CumulativeData(date, data["DIAMOND"].dailyResult[date].avg));
    }

    return [
      new charts.Series<CumulativeData, String>(
        id: AVG_WOOD,
        domainFn: (CumulativeData cD, _) => cD.date,
        measureFn: (CumulativeData cD, _) => cD.data,
        data: dataWood,
        // Set a label accessor to control the text of the bar label.
        labelAccessorFn: (CumulativeData cD, _) => '${cD.data.toString()} €',
        //Change fill color
        colorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
        fillColorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
      ),
      new charts.Series<CumulativeData, String>(
        id: AVG_STONE,
        domainFn: (CumulativeData cD, _) => cD.date,
        measureFn: (CumulativeData cD, _) => cD.data,
        data: dataStone,
        // Set a label accessor to control the text of the bar label.
        labelAccessorFn: (CumulativeData cD, _) => '${cD.data.toString()} €',
        //Change fill color
        colorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
        fillColorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault.lighter.lighter,
      ),
      new charts.Series<CumulativeData, String>(
        id: AVG_IRON,
        domainFn: (CumulativeData cD, _) => cD.date,
        measureFn: (CumulativeData cD, _) => cD.data,
        data: dataIron,
        // Set a label accessor to control the text of the bar label.
        labelAccessorFn: (CumulativeData cD, _) => '${cD.data.toString()} €',
        //Change fill color
        colorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
        fillColorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault.darker,
      ),
      new charts.Series<CumulativeData, String>(
        id: AVG_DIAMOND,
        domainFn: (CumulativeData cD, _) => cD.date,
        measureFn: (CumulativeData cD, _) => cD.data,
        data: dataDiamond,
        // Set a label accessor to control the text of the bar label.
        labelAccessorFn: (CumulativeData cD, _) => '${cD.data.toString()} €',
        //Change fill color
        colorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
        fillColorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault.lighter,
      ),

    ];
  }

  static String getTitle(List<charts.Series<CumulativeData, String>> chartList){
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

/// Class to show data in charts
class CumulativeData {
  final String date;
  final double data;

  CumulativeData(this.date, this.data);

  @override
  String toString() {
    return 'CumulativeData{date: $date, data: $data}';
  }


}