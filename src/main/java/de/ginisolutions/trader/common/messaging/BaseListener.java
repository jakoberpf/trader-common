package de.ginisolutions.trader.common.messaging;

import net.engio.mbassy.listener.Listener;
import net.engio.mbassy.listener.References;

@Listener(references = References.Strong)
public interface BaseListener {
}
