/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umich.opensearch.kde.params.denoise;

/**
 * @author Dmitry Avtonomov
 */
public class NumberedParamsDefault extends NumberedParams {

  public NumberedParamsDefault() {
  }

  @Override
  public int getNumberOfParameters() {
    return 0;
  }

  @Override
  public double getParameter(int i) {
    throw new IllegalStateException(
        "You can't get parameters when getNumberOfParameters() returns 0.");
  }

}