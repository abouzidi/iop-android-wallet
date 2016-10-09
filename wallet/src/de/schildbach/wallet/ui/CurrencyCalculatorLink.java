/*
 * Copyright 2013-2015 the original author or authors.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.schildbach.wallet.ui;

import javax.annotation.Nullable;

import org.bitcoinj.core.Coin;
import org.bitcoinj.utils.ExchangeRate;
import org.bitcoinj.utils.Fiat;

import android.util.Log;
import android.view.View;

import de.schildbach.wallet.Constants;
import de.schildbach.wallet.ui.CurrencyAmountView.Listener;

/**
 * @author Andreas Schildbach
 */
public final class CurrencyCalculatorLink
{
	private final CurrencyAmountView IoPAmountView;
	private final CurrencyAmountView localAmountView;

	private Listener listener = null;
	private boolean enabled = true;
	private ExchangeRate exchangeRate = null;
	private boolean exchangeDirection = true;

	private final CurrencyAmountView.Listener IoPAmountViewListener = new CurrencyAmountView.Listener()
	{
		@Override
		public void changed()
		{
			if (IoPAmountView.getAmount() != null)
				setExchangeDirection(true);
			else
				localAmountView.setHint(null);

			if (listener != null)
				listener.changed();
		}

		@Override
		public void focusChanged(final boolean hasFocus)
		{
			if (listener != null)
				listener.focusChanged(hasFocus);
		}
	};

	private final CurrencyAmountView.Listener localAmountViewListener = new CurrencyAmountView.Listener()
	{
		@Override
		public void changed()
		{
			if (localAmountView.getAmount() != null)
				setExchangeDirection(false);
			else
				IoPAmountView.setHint(null);

			if (listener != null)
				listener.changed();
		}

		@Override
		public void focusChanged(final boolean hasFocus)
		{
			if (listener != null)
				listener.focusChanged(hasFocus);
		}
	};

	public CurrencyCalculatorLink(final CurrencyAmountView IoPAmountView, final CurrencyAmountView localAmountView)
	{
		this.IoPAmountView = IoPAmountView;
		this.IoPAmountView.setListener(IoPAmountViewListener);

		this.localAmountView = localAmountView;
		this.localAmountView.setListener(localAmountViewListener);

		update();
	}

	public void setListener(@Nullable final Listener listener)
	{
		this.listener = listener;
	}

	public void setEnabled(final boolean enabled)
	{
		this.enabled = enabled;

		update();
	}

	public void setExchangeRate(final ExchangeRate exchangeRate)
	{
		this.exchangeRate = exchangeRate;

		update();
	}

	public ExchangeRate getExchangeRate()
	{
		return exchangeRate;
	}

	@Nullable
	public Coin getAmount()
	{
		if (exchangeDirection)
		{
			return (Coin) IoPAmountView.getAmount();
		}
		else if (exchangeRate != null)
		{
			final Fiat localAmount = (Fiat) localAmountView.getAmount();
			if (localAmount == null)
				return null;
			try
			{
				final Coin IoPAmount = exchangeRate.fiatToCoin(localAmount);
				if (((Coin) IoPAmount).isGreaterThan(Constants.NETWORK_PARAMETERS.getMaxMoney()))
					throw new ArithmeticException();
				return IoPAmount;
			}
			catch (ArithmeticException x)
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}

	public boolean hasAmount()
	{
		return getAmount() != null;
	}

	static int i = 0;

	private void update()
	{
		Log.i(getClass().getName(),"update times: "+i++);
		IoPAmountView.setEnabled(enabled);

		if (exchangeRate != null)
		{
			localAmountView.setEnabled(enabled);
			localAmountView.setCurrencySymbol(exchangeRate.fiat.currencyCode);

			if (exchangeDirection)
			{
				final Coin IoPAmount = (Coin) IoPAmountView.getAmount();
				if (IoPAmount != null)
				{
					localAmountView.setAmount(null, false);
					localAmountView.setHint(exchangeRate.coinToFiat(IoPAmount));
					IoPAmountView.setHint(null);
				}
			}
			else
			{
				final Fiat localAmount = (Fiat) localAmountView.getAmount();
				if (localAmount != null)
				{
					localAmountView.setHint(null);
					IoPAmountView.setAmount(null, false);
					try
					{
						final Coin IoPAmount = exchangeRate.fiatToCoin(localAmount);
						if (((Coin) IoPAmount).isGreaterThan(Constants.NETWORK_PARAMETERS.getMaxMoney()))
							throw new ArithmeticException();
						IoPAmountView.setHint(IoPAmount);
					}
					catch (final ArithmeticException x)
					{
						IoPAmountView.setHint(null);
					}
				}
			}
		}
		else
		{
			localAmountView.setEnabled(false);
			localAmountView.setHint(null);
			IoPAmountView.setHint(null);
		}
	}

	public void setExchangeDirection(final boolean exchangeDirection)
	{
		this.exchangeDirection = exchangeDirection;

		update();
	}

	public boolean getExchangeDirection()
	{
		return exchangeDirection;
	}

	public View activeTextView()
	{
		if (exchangeDirection)
			return IoPAmountView.getTextView();
		else
			return localAmountView.getTextView();
	}

	public void requestFocus()
	{
		activeTextView().requestFocus();
	}

	public void setIoPAmount(final Coin amount)
	{
		final Listener listener = this.listener;
		this.listener = null;

		IoPAmountView.setAmount(amount, true);

		this.listener = listener;
	}

	public void setNextFocusId(final int nextFocusId)
	{
		IoPAmountView.setNextFocusId(nextFocusId);
		localAmountView.setNextFocusId(nextFocusId);
	}
}
