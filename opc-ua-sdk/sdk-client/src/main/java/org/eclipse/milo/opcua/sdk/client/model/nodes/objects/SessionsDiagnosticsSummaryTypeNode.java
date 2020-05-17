package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SessionDiagnosticsArrayTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SessionSecurityDiagnosticsArrayTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.SessionsDiagnosticsSummaryType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class SessionsDiagnosticsSummaryTypeNode extends BaseObjectTypeNode implements SessionsDiagnosticsSummaryType {
    public SessionsDiagnosticsSummaryTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                              QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                              UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public SessionDiagnosticsDataType[] getSessionDiagnosticsArray() throws UaException {
        SessionDiagnosticsArrayTypeNode node = getSessionDiagnosticsArrayNode();
        return (SessionDiagnosticsDataType[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setSessionDiagnosticsArray(SessionDiagnosticsDataType[] sessionDiagnosticsArray)
        throws UaException {
        SessionDiagnosticsArrayTypeNode node = getSessionDiagnosticsArrayNode();
        node.setValue(new Variant(sessionDiagnosticsArray));
    }

    @Override
    public SessionDiagnosticsDataType[] readSessionDiagnosticsArray() throws UaException {
        try {
            return readSessionDiagnosticsArrayAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSessionDiagnosticsArray(SessionDiagnosticsDataType[] sessionDiagnosticsArray)
        throws UaException {
        try {
            writeSessionDiagnosticsArrayAsync(sessionDiagnosticsArray).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SessionDiagnosticsDataType[]> readSessionDiagnosticsArrayAsync(
    ) {
        return getSessionDiagnosticsArrayNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (SessionDiagnosticsDataType[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeSessionDiagnosticsArrayAsync(
        SessionDiagnosticsDataType[] sessionDiagnosticsArray) {
        DataValue value = DataValue.valueOnly(new Variant(sessionDiagnosticsArray));
        return getSessionDiagnosticsArrayNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public SessionDiagnosticsArrayTypeNode getSessionDiagnosticsArrayNode() throws UaException {
        try {
            return getSessionDiagnosticsArrayNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SessionDiagnosticsArrayTypeNode> getSessionDiagnosticsArrayNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SessionDiagnosticsArray", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=2196"), false);
        return future.thenApply(node -> (SessionDiagnosticsArrayTypeNode) node);
    }

    @Override
    public SessionSecurityDiagnosticsDataType[] getSessionSecurityDiagnosticsArray() throws
        UaException {
        SessionSecurityDiagnosticsArrayTypeNode node = getSessionSecurityDiagnosticsArrayNode();
        return (SessionSecurityDiagnosticsDataType[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setSessionSecurityDiagnosticsArray(
        SessionSecurityDiagnosticsDataType[] sessionSecurityDiagnosticsArray) throws UaException {
        SessionSecurityDiagnosticsArrayTypeNode node = getSessionSecurityDiagnosticsArrayNode();
        node.setValue(new Variant(sessionSecurityDiagnosticsArray));
    }

    @Override
    public SessionSecurityDiagnosticsDataType[] readSessionSecurityDiagnosticsArray() throws
        UaException {
        try {
            return readSessionSecurityDiagnosticsArrayAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSessionSecurityDiagnosticsArray(
        SessionSecurityDiagnosticsDataType[] sessionSecurityDiagnosticsArray) throws UaException {
        try {
            writeSessionSecurityDiagnosticsArrayAsync(sessionSecurityDiagnosticsArray).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SessionSecurityDiagnosticsDataType[]> readSessionSecurityDiagnosticsArrayAsync(
    ) {
        return getSessionSecurityDiagnosticsArrayNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (SessionSecurityDiagnosticsDataType[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeSessionSecurityDiagnosticsArrayAsync(
        SessionSecurityDiagnosticsDataType[] sessionSecurityDiagnosticsArray) {
        DataValue value = DataValue.valueOnly(new Variant(sessionSecurityDiagnosticsArray));
        return getSessionSecurityDiagnosticsArrayNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public SessionSecurityDiagnosticsArrayTypeNode getSessionSecurityDiagnosticsArrayNode() throws
        UaException {
        try {
            return getSessionSecurityDiagnosticsArrayNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SessionSecurityDiagnosticsArrayTypeNode> getSessionSecurityDiagnosticsArrayNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SessionSecurityDiagnosticsArray", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=2243"), false);
        return future.thenApply(node -> (SessionSecurityDiagnosticsArrayTypeNode) node);
    }
}
